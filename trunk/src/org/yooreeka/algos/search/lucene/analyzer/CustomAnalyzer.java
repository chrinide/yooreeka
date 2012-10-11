package org.yooreeka.algos.search.lucene.analyzer;

import java.io.IOException;
import java.io.Reader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.StopFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.util.CharArraySet;
import org.apache.lucene.analysis.util.StopwordAnalyzerBase;
import org.apache.lucene.util.Version;

public class CustomAnalyzer extends StopwordAnalyzerBase {

	/** Default maximum allowed token length */
	public static final int DEFAULT_MAX_TOKEN_LENGTH = 255;

	private int maxTokenLength = DEFAULT_MAX_TOKEN_LENGTH;

	/**
	 * An unmodifiable set containing some common English words that are usually
	 * not useful for searching.
	 */
	public static final CharArraySet STOP_WORDS_SET = StopAnalyzer.ENGLISH_STOP_WORDS_SET;

	private static final String[] ADDITIONAL_STOP_WORDS = { "should", "would",
			"from", "up", "i", "s", "it", "his", "has", "he", "she", "her",
			"said", "been", "being", "final", "now", "hour", "minute",
			"second", "stop", "start", "first", "third", "fast", "slow",
			"large", "small" };

	private static CharArraySet MERGED_STOP_WORDS;

	static {
		MERGED_STOP_WORDS = new CharArraySet(Version.LUCENE_40,
				STOP_WORDS_SET.size() + ADDITIONAL_STOP_WORDS.length, true);
	}

	/**
	 * Builds an analyzer with the given stop words.
	 * 
	 * @param matchVersion
	 *            Lucene version to match See
	 *            {@link <a href="#version">above</a>}
	 * @param stopWords
	 *            stop words
	 */
	public CustomAnalyzer(Version matchVersion, CharArraySet stopWords) {

		super(matchVersion, stopWords);
	}

	public CustomAnalyzer(Version matchVersion) {
		this(matchVersion, MERGED_STOP_WORDS);
	}

	@Override
	protected TokenStreamComponents createComponents(final String fieldName,
			final Reader reader) {

		final StandardTokenizer src = new StandardTokenizer(matchVersion,
				reader);
		src.setMaxTokenLength(maxTokenLength);
		TokenStream tok = new StandardFilter(matchVersion, src);
		tok = new LowerCaseFilter(matchVersion, tok);
		tok = new StopFilter(matchVersion, tok, stopwords);
		return new TokenStreamComponents(src, tok) {
			@Override
			protected void setReader(final Reader reader) throws IOException {
				src.setMaxTokenLength(CustomAnalyzer.this.maxTokenLength);
				super.setReader(reader);
			}
		};
	}
}
