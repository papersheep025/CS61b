package ngrams;

import edu.princeton.cs.algs4.In;

import java.util.Collection;
import java.util.List;
import java.util.TreeMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {
    private WordSeries ownWordSeries;
    private TimeSeries yearDatas;

    private class WordSeries extends TreeMap<String,TimeSeries> {
        public WordSeries() {
            super();
        }
    }

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        In wordFileIn = new In(wordsFilename);
        In countsFileIn = new In(countsFilename);
        ownWordSeries = new WordSeries();
        yearDatas = new TimeSeries();

        // Read data from word file.
        while(!wordFileIn.isEmpty()){
            String nextLine = wordFileIn.readLine();
            String[] splitLine = nextLine.split("\t"); // Split the line by tab.

            if(!ownWordSeries.containsKey(splitLine[0])){ // If the word is not in ownWordSeries, create a new TimeSeries.
                TimeSeries temp = new TimeSeries();
                temp.put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
                ownWordSeries.put(splitLine[0], temp);
            } else{ // If the word already exists, just update the existing TimeSeries.
                ownWordSeries.get(splitLine[0]).put(Integer.parseInt(splitLine[1]), Double.parseDouble(splitLine[2]));
            }
        }

        // Read data from the counts file.
        while (!countsFileIn.isEmpty()){
            String nextLine = countsFileIn.readLine();
            String[] splitLine = nextLine.split(",");

            yearDatas.put(Integer.parseInt(splitLine[0]), Double.parseDouble(splitLine[1]));
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();

        for (int y = startYear; y < endYear + 1; y++) {
            double data = ownWordSeries.get(word).get(y);
            result.put(y, data);
        }
        return result;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        TimeSeries result = new TimeSeries();
        TimeSeries origin = ownWordSeries.get(word);

        if (origin == null) return result;

        List<Integer> yearList = origin.years();
        for (int y : yearList) {
            result.put(y, origin.get(y));
        }
        return result;
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return yearDatas;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        TimeSeries wordTimeSeries = ownWordSeries.get(word);

        for (int y = startYear; y < endYear + 1; y++) {
            result.put(y, wordTimeSeries.get(y) / yearDatas.get(y)); // Calculate the weight and store in result.
        }
        return result;
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        TimeSeries result = new TimeSeries();
        TimeSeries wordTimeSeries = ownWordSeries.get(word);

        if(wordTimeSeries == null) return result;

        List<Integer> yearsList = wordTimeSeries.years();
        for(int y : yearsList) {
            result.put(y,wordTimeSeries.get(y) / yearDatas.get(y));
        }
        return result;
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();

        for (int y = startYear; y < endYear + 1; y++) {
            double sum = 0.0;
            for (String w : words) {
                sum += weightHistory(w, startYear, endYear).get(y);
            }
            result.put(y, sum);
        }
        return result;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        TimeSeries result = new TimeSeries();

        for (int y = MIN_YEAR; y < MAX_YEAR + 1; y++) {
            double sum = 0.0;
            for (String w : words) {
                sum += weightHistory(w, MIN_YEAR, MAX_YEAR).get(y);
            }
            result.put(y, sum);
        }
        return result;
    }
}
