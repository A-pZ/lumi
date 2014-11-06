package lumi.function;

import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;


/**
 * 文字列操作のユーティリティクラス．
 *
 * @author A-pZ ( Serendipity 3 ./ as sundome goes by. )
 */
public class StringConverter {

	/**
	 * 半角カタカナの配列
	 */
	private static final String[] HANKAKU = { "ｱ", "ｲ", "ｳ", "ｴ", "ｵ", "ｶ",
			"ｷ", "ｸ", "ｹ", "ｺ", "ｻ", "ｼ", "ｽ", "ｾ", "ｿ", "ﾀ", "ﾁ", "ﾂ", "ﾃ",
			"ﾄ", "ﾅ", "ﾆ", "ﾇ", "ﾈ", "ﾉ", "ﾊ", "ﾋ", "ﾌ", "ﾍ", "ﾎ", "ﾏ", "ﾐ",
			"ﾑ", "ﾒ", "ﾓ", "ﾔ", "ﾕ", "ﾖ", "ﾗ", "ﾘ", "ﾙ", "ﾚ", "ﾛ", "ﾜ", "ｦ",
			"ﾝ", "ｶﾞ", "ｷﾞ", "ｸﾞ", "ｹﾞ", "ｺﾞ", "ｻﾞ", "ｼﾞ", "ｽﾞ", "ｾﾞ", "ｿﾞ",
			"ﾀﾞ", "ﾁﾞ", "ﾂﾞ", "ﾃﾞ", "ﾄﾞ", "ﾊﾞ", "ﾋﾞ", "ﾌﾞ", "ﾍﾞ", "ﾎﾞ", "ﾊﾟ",
			"ﾋﾟ", "ﾌﾟ", "ﾍﾟ", "ﾎﾟ", "ｧ", "ｨ", "ｩ", "ｪ", "ｫ", "ｯ", "ｬ", "ｭ",
			"ｮ", "ｰ", " " };

	/**
	 * 全角カタカナの配列
	 */
	private static final String[] ZENKAKU = { "ア", "イ", "ウ", "エ", "オ", "カ",
			"キ", "ク", "ケ", "コ", "サ", "シ", "ス", "セ", "ソ", "タ", "チ", "ツ", "テ",
			"ト", "ナ", "ニ", "ヌ", "ネ", "ノ", "ハ", "ヒ", "フ", "ヘ", "ホ", "マ", "ミ",
			"ム", "メ", "モ", "ヤ", "ユ", "ヨ", "ラ", "リ", "ル", "レ", "ロ", "ワ", "ヲ",
			"ン", "ガ", "ギ", "グ", "ゲ", "ゴ", "ザ", "ジ", "ズ", "ゼ", "ゾ", "ダ", "ヂ",
			"ヅ", "デ", "ド", "バ", "ビ", "ブ", "ベ", "ボ", "パ", "ピ", "プ", "ペ", "ポ",
			"ァ", "ィ", "ゥ", "ェ", "ォ", "ッ", "ャ", "ュ", "ョ", "ー", "　" };

	/**
	 * 小文字半角文字の配列
	 */
	private static final String[] KOMOJI = { "ァ", "ィ", "ゥ", "ェ", "ォ", "ッ", "ャ",
			"ュ", "ョ", "ヮ" };

	/**
	 * 大文字半角文字の配列
	 */
	private static final String[] OMOJI = { "ア", "イ", "ウ", "エ", "オ", "ツ", "ヤ",
			"ユ", "ヨ", "ワ" };

	/**
	 * 半角英数字の配列
	 */
	private static final String[] HANKAKU_EISU = { "A", "B", "C", "D", "E",
			"F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
			"S", "T", "U", "V", "W", "X", "Y", "Z", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "0" };

	/**
	 * 全角英数字の配列
	 */
	private static final String[] ZENKAKU_EISU = { "Ａ", "Ｂ", "Ｃ", "Ｄ", "Ｅ",
			"Ｆ", "Ｇ", "Ｈ", "Ｉ", "Ｊ", "Ｋ", "Ｌ", "Ｍ", "Ｎ", "Ｏ", "Ｐ", "Ｑ", "Ｒ",
			"Ｓ", "Ｔ", "Ｕ", "Ｖ", "Ｗ", "Ｘ", "Ｙ", "Ｚ", "１", "２", "３", "４", "５",
			"６", "７", "８", "９", "０" };

	/**
	 * 英文字の配列（半角大文字、全角大文字、半角小文字、全角小文字）
	 */
	private static final String[][] EIMOJI = { { "A", "Ａ", "a", "ａ" },
			{ "B", "Ｂ", "b", "ｂ" }, { "C", "Ｃ", "c", "ｃ" },
			{ "D", "Ｄ", "d", "ｄ" }, { "E", "Ｅ", "e", "ｅ" },
			{ "F", "Ｆ", "f", "ｆ" }, { "G", "Ｇ", "g", "ｇ" },
			{ "H", "Ｈ", "h", "ｈ" }, { "I", "Ｉ", "i", "ｉ" },
			{ "J", "Ｊ", "j", "ｊ" }, { "K", "Ｋ", "k", "ｋ" },
			{ "L", "Ｌ", "l", "ｌ" }, { "M", "Ｍ", "m", "ｍ" },
			{ "N", "Ｎ", "n", "ｎ" }, { "O", "Ｏ", "o", "ｏ" },
			{ "P", "Ｐ", "p", "ｐ" }, { "Q", "Ｑ", "q", "ｑ" },
			{ "R", "Ｒ", "r", "ｒ" }, { "S", "Ｓ", "s", "ｓ" },
			{ "T", "Ｔ", "t", "ｔ" }, { "U", "Ｕ", "u", "ｕ" },
			{ "V", "Ｖ", "v", "ｖ" }, { "W", "Ｗ", "w", "ｗ" },
			{ "X", "Ｘ", "x", "ｘ" }, { "Y", "Ｙ", "y", "ｙ" },
			{ "Z", "Ｚ", "z", "ｚ" } };

	/**
	 * 数字の配列（半角、全角）
	 */
	private static final String[][] SUUJI = { { "1", "１" }, { "2", "２" },
			{ "3", "３" }, { "4", "４" }, { "5", "５" }, { "6", "６" },
			{ "7", "７" }, { "8", "８" }, { "9", "９" }, { "0", "０" } };

	/**
	 * かなの配列（半角カタカナ、全角カタカナ、ひらがな）
	 */
	private static final String[][] KANA = { { "ｱ", "ア", "あ" },
			{ "ｲ", "イ", "い" }, { "ｳ", "ウ", "う" }, { "ｴ", "エ", "え" },
			{ "ｵ", "オ", "お" }, { "ｶ", "カ", "か" }, { "ｷ", "キ", "き" },
			{ "ｸ", "ク", "く" }, { "ｹ", "ケ", "け" }, { "ｺ", "コ", "こ" },
			{ "ｻ", "サ", "さ" }, { "ｼ", "シ", "し" }, { "ｽ", "ス", "す" },
			{ "ｾ", "セ", "せ" }, { "ｿ", "ソ", "そ" }, { "ﾀ", "タ", "た" },
			{ "ﾁ", "チ", "ち" }, { "ﾂ", "ツ", "つ" }, { "ﾃ", "テ", "て" },
			{ "ﾄ", "ト", "と" }, { "ﾅ", "ナ", "な" }, { "ﾆ", "ニ", "に" },
			{ "ﾇ", "ヌ", "ぬ" }, { "ﾈ", "ネ", "ね" }, { "ﾉ", "ノ", "の" },
			{ "ﾊ", "ハ", "は" }, { "ﾋ", "ヒ", "ひ" }, { "ﾌ", "フ", "ふ" },
			{ "ﾍ", "ヘ", "へ" }, { "ﾎ", "ホ", "ほ" }, { "ﾏ", "マ", "ま" },
			{ "ﾐ", "ミ", "み" }, { "ﾑ", "ム", "む" }, { "ﾒ", "メ", "め" },
			{ "ﾓ", "モ", "も" }, { "ﾔ", "ヤ", "や" }, { "ﾕ", "ユ", "ゆ" },
			{ "ﾖ", "ヨ", "よ" }, { "ﾗ", "ラ", "ら" }, { "ﾘ", "リ", "り" },
			{ "ﾙ", "ル", "る" }, { "ﾚ", "レ", "れ" }, { "ﾛ", "ロ", "ろ" },
			{ "ﾜ", "ワ", "わ" }, { "ｦ", "ヲ", "を" }, { "ﾝ", "ン", "ん" },
			{ "ｶﾞ", "ガ", "が" }, { "ｷﾞ", "ギ", "ぎ" }, { "ｸﾞ", "グ", "ぐ" },
			{ "ｹﾞ", "ゲ", "げ" }, { "ｺﾞ", "ゴ", "ご" }, { "ｻﾞ", "ザ", "ざ" },
			{ "ｼﾞ", "ジ", "じ" }, { "ｽﾞ", "ズ", "ず" }, { "ｾﾞ", "ゼ", "ぜ" },
			{ "ｿﾞ", "ゾ", "ぞ" }, { "ﾀﾞ", "ダ", "だ" }, { "ﾁﾞ", "ヂ", "ぢ" },
			{ "ﾂﾞ", "ヅ", "づ" }, { "ﾃﾞ", "デ", "で" }, { "ﾄﾞ", "ド", "ど" },
			{ "ﾊﾞ", "バ", "ば" }, { "ﾋﾞ", "ビ", "び" }, { "ﾌﾞ", "ブ", "ぶ" },
			{ "ﾍﾞ", "ベ", "べ" }, { "ﾎﾞ", "ボ", "ぼ" }, { "ﾊﾟ", "パ", "ぱ" },
			{ "ﾋﾟ", "ピ", "ぴ" }, { "ﾌﾟ", "プ", "ぷ" }, { "ﾍﾟ", "ペ", "ぺ" },
			{ "ﾎﾟ", "ポ", "ぽ" }, { "ｧ", "ァ", "ぁ" }, { "ｨ", "ィ", "ぃ" },
			{ "ｩ", "ゥ", "ぅ" }, { "ｪ", "ェ", "ぇ" }, { "ｫ", "ォ", "ぉ" },
			{ "ｯ", "ッ", "っ" }, { "ｬ", "ャ", "ゃ" }, { "ｭ", "ュ", "ゅ" },
			{ "ｮ", "ョ", "ょ" } };

	/**
	 * ゎをわに、ヮをワに置き換える
	 */
	private static final String[][] SPECIAL_CHARA = { { "ゎ", "わ" },
			{ "ヮ", "ワ" } };

	/**
	 * 記号の配列(半角、全角)
	 */
	private static final String[][] KIGOU = { { " ", "　" }, { "ｰ", "ー" },
			{ "-", "－" }, { "-", "‐" }, { "@", "＠" }, { "_", "＿" },
			{ ".", "．" }, { "#", "＃" }, { "&", "＆" }, { "{", "｛" },
			{ "}", "｝" }, { "|", "｜" }, { "~", "～" }, { "=", "＝" },
			{ "?", "？" }, { "^", "＾" }, { "!", "！" }, { "*", "＊" },
			{ "+", "＋" }, { "/", "／" }, { "\\", "￥" }, { "`", "‘" },
			{ "'", "’" }, { "\"", "”" }, { "$", "＄" }, { ":", "：" },
			{ ";", "；" }, { ">", "＞" }, { "<", "＜" }, { "[", "［" },
			{ "]", "］" }, { "%", "％" }, { "(", "（" }, { ")", "）" },
			{ ",", "，" }, { "｡", "。" }, { "､", "、" }, { "･", "・" },
			{ "｢", "「" }, { "｣", "」" } };

	/**
	 * 氏名（フリガナ）の記号チェック用
	 */
	private static final String[][] FURIGANA_KIGOU = { { " ", "　" },
			{ "ｰ", "ー" }, { "ｳﾞ", "ヴ" } };


	/**
	 * 電話番号の記号チェック用（半角長音を半角ハイフンに変換する）
	 */
	private static final String[][] TEL_HYPHEN = { { "ｰ", "-" } };

	/**
	 * /** 全角カナ小文字を全角カナ大文字に変換する。 入力文字列に全角カナ小文字以外が含まれる場合、その文字については 変換せずにそのまま返す。
	 *
	 * @param s
	 *            変換したい全角カナ小文字
	 * @return 変換後の全角カナ大文字
	 */
	public static String komojiToOmoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < KOMOJI.length; n++) {
				if (s.substring(i, i + 1).equals(KOMOJI[n])) {
					ans.append(OMOJI[n]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 半角カタカナを全角カタカナに変換します．
	 *
	 * @param s
	 *            変換したい文字
	 * @return 全角に変換後の文字
	 */
	public static String hankakuToZenkaku(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < HANKAKU.length; n++) {
				// 先に濁音（２バイト）の比較をするべき
				if ((i + 2) <= s.length()) {
					if (s.substring(i, i + 2).equals(HANKAKU[n])) {
						ans.append(ZENKAKU[n]);
						isConv = true;
						i++;
						break;
					}
				}
			}
			if (isConv) {
				continue;
			}
			for (int n = 0; n < HANKAKU.length; n++) {
				// 濁音なし（１バイト）の比較
				if (s.substring(i, i + 1).equals(HANKAKU[n])) {
					ans.append(ZENKAKU[n]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(英字(大文字)と数字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 全角に変換後の文字
	 */
	public static String hankakuToZenkakuEisu(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < HANKAKU_EISU.length; n++) {
				if (s.substring(i, i + 1).equals(HANKAKU_EISU[n])) {
					ans.append(ZENKAKU_EISU[n]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 全角から半角に変換します．
	 *
	 * @param s
	 *            変換したい文字
	 * @return 半角に変換後の文字
	 */
	public static String zenkakuToHankaku(String s) {
		s = hankakuToZenkaku(s);
		s = komojiToOmoji(s);
		return zenkakuToHankakuNoOmoji(s);
	}

	/**
	 * 全角カタカナをを半角カタカナに変換します．
	 *
	 * @param s
	 *            変換したい文字
	 * @return 半角に変換後の文字
	 */
	public static String zenkakuToHankakuNoOmoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < HANKAKU.length; n++) {
				if (s.substring(i, i + 1).equals(ZENKAKU[n])) {
					ans.append(HANKAKU[n]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 全角英数字を半角英数字に変換します． 文字列に全角英数字以外が混在する場合、その文字は変換しない。
	 *
	 * @param s
	 *            変換したい文字
	 * @return 半角に変換後の文字
	 */
	public static String zenkakuToHankakuEisu(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < HANKAKU_EISU.length; n++) {
				if (s.substring(i, i + 1).equals(ZENKAKU_EISU[n])) {
					ans.append(HANKAKU_EISU[n]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 半角文字かどうかを判定します．
	 *
	 * @param s
	 *            判定したい文字
	 * @return 半角:<code>true</code> 全角:<code>false</code>
	 */
	public static boolean isHankaku(String s) {
		if (s == null || s.length() < 1) {
			return false;
		}
		byte[] bytes = s.getBytes();
		int beams = s.length();
		return (beams == bytes.length);
	}

	/**
	 * DOT区切り文字列の最後の区画を取得します．
	 *
	 * @param value
	 * @return String
	 */
	public static String getLastParam(String value) {
		String result = value;
		StringTokenizer st = new StringTokenizer(result, ".");
		while (st.hasMoreTokens()) {
			result = st.nextToken();
		}
		return result;
	}

	/**
	 * 全角スペースのトリムを行います．
	 *
	 * @param str
	 *            トリムする文字
	 * @return トリム後の文字
	 */
	public static String trimDoubleSpace(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		str = str.trim();
		str = str.replaceAll("^[　]+", "");
		str = str.replaceAll("[　]+$", "");
		return str;
	}

	/**
	 * 記号を取り除いた文字を返します．
	 *
	 * @param str
	 * @return 記号が取り除かれた文字列
	 */
	public static String removeSymbol(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		return str.replaceAll("[ -/:-~]", "");
	}


	/**
	 * 半角かどうかを返します．
	 *
	 * @param str
	 *            判定する文字列
	 * @return 半角の場合<code>true</code>,それ以外<code>false</code>
	 */
	public static boolean isHankakuStr(String str) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return str.matches("^[ -~｡-ﾟ]+$");
	}

	/**
	 * 半角英数字かどうかを返します．
	 *
	 * @param str
	 *            判定する文字列
	 * @return 半角の場合<code>true</code>,それ以外<code>false</code>
	 */
	public static boolean isHankakuAlphaOrNum(String str) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return str.matches("^[a-zA-Z0-9]+$");
	}

	/**
	 * 半角記号英数字(英字は大文字）と全角カタカナかどうかをチェックします．
	 *
	 * @param s
	 *            判定する文字列
	 * @return 半角英数字と全角カタカナの場合<code>true</code>,それ以外<code>true</code>
	 */
	public static boolean isSingleByteAndDoubleKana(String s) {
		if (s == null || s.length() < 1) {
			return true;
		}
		/** 半角文字，全角カタカナチェック */
		boolean ans = s.matches("^[ -~｡-ﾟァ-ヶー　 ]+$");
		/** 英小文字チェック */
		boolean alph = s.matches("^[^a-z]+$");
		return (ans && alph);
	}

	/**
	 * 半角英数字（英字は大文字）をチェックします．
	 *
	 * @param s
	 *            判定する文字列
	 * @return 半角英数字の場合<code>true</code>,それ以外<code>true</code>
	 */
	public static boolean isSigleByteExceptLowercase(String s) {
		if (s == null || s.length() < 1) {
			return true;
		}
		/** 半角チェック */
		boolean ans = isHankakuStr(s);
		/** 英小文字チェック */
		boolean alph = s.matches("^[^a-z]+$");
		return (ans && alph);
	}


	/**
	 * 数字以外をすべて削除し、数値だけにして返す
	 *
	 * 全角数字は半角数字に変換する
	 *
	 * @throw Null文字の場合はNullPointerExceptionをスロー
	 *
	 * @param buf
	 * @return
	 */
	public static String removeCharAndChangeZenkakuNum(String buf) {
		String hankakuValue = StringConverter.zenkakuToHankakuEisu(buf);
		String number = hankakuValue.replaceAll("[^0-9]", "");
		return number;
	}

	/**
	 * 半角、全角数字かどうかを返します．
	 *
	 * @param str
	 *            判定する文字列
	 * @return 半角、全角数字の場合<code>true</code>,それ以外<code>false</code>
	 */
	public static boolean isHankakuStrOrZenkakuNum(String str) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return str.matches("^[ -~｡-ﾟ０-９]+$");
	}

	/**
	 * 半角英数字（英字は大文字）、全角数字をチェックします．
	 *
	 * @param s
	 *            判定する文字列
	 * @return 半角英数字、全角数字の場合<code>true</code>,それ以外<code>true</code>
	 */
	public static boolean isSigleByteExceptLowercaseOrZenkakuNum(String s) {
		if (s == null || s.length() < 1) {
			return true;
		}
		/** 半角チェック */
		boolean ans = isHankakuStrOrZenkakuNum(s);
		/** 英小文字チェック */
		boolean alph = s.matches("^[^a-z]+$");
		return (ans && alph);
	}

	/**
	 * 半角数字・全角数字かどうかを返します．
	 *
	 * @param str
	 *            判定する文字列
	 * @return 半角数字・全角数字の場合<code>true</code>,それ以外<code>false</code>
	 */
	public static boolean isHankakuNumOrZenkakuNum(String str) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return str.matches("^[0-9０-９]+$");
	}

	/**
	 * 後ろスペースのトリムを行います。 （全角スペース、半角スペース）
	 *
	 * @param str
	 *            トリムする文字
	 * @return トリム後の文字
	 */
	public static String trimRSpace(String str) {
		if (StringUtils.isBlank(str)) {
			return str;
		}
		str = str.replaceAll("[　 ]+$", "");
		return str;
	}

	/**
	 * 変換結果を戻す
	 *
	 * @param s
	 *            比較対照の文字
	 * @param convertArray
	 *            比較する配列文字列
	 * @param j
	 *            比較する配列文字列の要素インデックス
	 * @param k
	 *            変換結果として格納する配列文字列の要素インデックス
	 * @return
	 */
	private static StringBuffer convertCharacter(String s,
			String[][] convertArray, int j, int k) {
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;
			for (int n = 0; n < convertArray.length; n++) {
				if (s.substring(i, i + 1).equals(convertArray[n][j])) {
					ans.append(convertArray[n][k]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans;
	}

	/**
	 * 全角を半角に変換します．(数字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuSuuji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, SUUJI, 1, 0);
		return ans.toString();
	}

	/**
	 * 全角を半角に変換します．(英大文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuEioomoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, EIMOJI, 1, 0);
		return ans.toString();
	}

	/**
	 * 全角を半角に変換します．(英小文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuEikomoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, EIMOJI, 3, 2);
		return ans.toString();
	}

	/**
	 * 英小文字→英大文字に変換します．
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String komojiToOomojiEimoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, EIMOJI, 3, 1);
		ans = convertCharacter(ans.toString(), EIMOJI, 2, 0);
		return ans.toString();
	}

	/**
	 * 全角を半角に変換します．(ひらがな→カタカナ)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuHiragana(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, KANA, 2, 0);
		return ans.toString();
	}

	/**
	 * 全角を半角に変換します．(カタカナ文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuKana(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, KANA, 1, 0);
		return ans.toString();
	}

	/**
	 * 全角を半角に変換します．(記号文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToHankakuKigou(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, KIGOU, 1, 0);
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(数字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuSuuji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, SUUJI, 0, 1);
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(英大文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuEioomoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, EIMOJI, 0, 1);
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(英小文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuEikomoji(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, EIMOJI, 2, 3);
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(カタカナ文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuKana(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;

			for (int n = 0; n < KANA.length; n++) {
				// 先に濁音（２バイト）の比較
				if ((i + 2) <= s.length()) {
					if (s.substring(i, i + 2).equals(KANA[n][0])) {
						ans.append(KANA[n][1]);
						isConv = true;
						i++;
						break;
					}
				}
			}
			if (isConv) {
				continue;
			}
			for (int n = 0; n < KANA.length; n++) {
				// 濁音なし（１バイト）の比較
				if (s.substring(i, i + 1).equals(KANA[n][0])) {
					ans.append(KANA[n][1]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(記号文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuKigou(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, KIGOU, 0, 1);
		return ans.toString();
	}

	/**
	 * 半角を全角に変換します．(氏名（フリガナ）用記号文字)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToZenkakuFuriganaKigou(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = new StringBuffer("");
		for (int i = 0; i < s.length(); i++) {
			boolean isConv = false;

			for (int n = 0; n < FURIGANA_KIGOU.length; n++) {
				// 先に濁音（２バイト）の比較
				if ((i + 2) <= s.length()) {
					if (s.substring(i, i + 2).equals(FURIGANA_KIGOU[n][0])) {
						ans.append(FURIGANA_KIGOU[n][1]);
						isConv = true;
						i++;
						break;
					}
				}
			}
			if (isConv) {
				continue;
			}
			for (int n = 0; n < FURIGANA_KIGOU.length; n++) {
				// １バイトの比較
				if (s.substring(i, i + 1).equals(FURIGANA_KIGOU[n][0])) {
					ans.append(FURIGANA_KIGOU[n][1]);
					isConv = true;
					break;
				}
			}
			if (!isConv) {
				ans.append(s.substring(i, i + 1));
			}
		}
		return ans.toString();
	}

	/**
	 * 全角を全角に変換します．(ひらがな→カタカナ)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToZenkakuHiragana(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, KANA, 2, 1);
		return ans.toString();
	}

	/**
	 * 数字、ハイフンのみかどうかを返します．
	 *
	 * @param str
	 *            判定する文字列
	 * @return 半角数字・全角数字・ハイフンの場合<code>true</code>,それ以外<code>false</code>
	 */
	public static boolean isNumberOrHyphen(String str) {
		if (StringUtils.isBlank(str)) {
			return true;
		}
		return str.matches("^[0-9０-９-ー－]+$");
	}

	/**
	 * 半角長音を半角ハイフンに変換します．(SRM入会、登録内容変更の電話番号用)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String hankakuToHankakuHyphen(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, TEL_HYPHEN, 0, 1);
		return ans.toString();
	}

	/**
	 * 全角小文字のヮ、ゎを全角ワ、わに変換します．(SRM入会、登録内容変更用)
	 *
	 * @param s
	 *            変換したい文字
	 * @return 変換後の文字
	 */
	public static String zenkakuToZenkakuSpecialChar(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, SPECIAL_CHARA, 0, 1);
		return ans.toString();
	}

	/**
	 * 全角スペース、長音、ヴを半角に変換します。
	 *
	 * @param s
	 * @return
	 */
	public static String zenkakuToHankakuFuriganaKigou(String s) {
		if (s == null) {
			return s;
		}
		StringBuffer ans = convertCharacter(s, FURIGANA_KIGOU, 1, 0);
		return ans.toString();
	}


	/**
	 * バイト配列を16進数の文字列に変換する。
	 *
	 * @param bytes
	 *            バイト配列
	 * @return 16進数の文字列
	 */
	public static String asHex(byte bytes[]) {
		// バイト配列の２倍の長さの文字列バッファを生成。
		StringBuffer strbuf = new StringBuffer(bytes.length * 2);
		// バイト配列の要素数分、処理を繰り返す。
		for (int index = 0; index < bytes.length; index++) {
			// バイト値を自然数に変換。
			int bt = bytes[index] & 0xff;
			// バイト値が0x10以下か判定。
			if (bt < 0x10) {
				// 0x10以下の場合、文字列バッファに0を追加。
				strbuf.append("0");
			}
			// バイト値を16進数の文字列に変換して、文字列バッファに追加。
			strbuf.append(Long.toString(bt, 16));
		}
		// / 16進数の文字列を返す。
		return strbuf.toString();
	}

	/**
	 * 16進数の文字列をバイト配列に変換する。
	 *
	 * @param hex
	 *            16進数の文字列
	 * @return バイト配列
	 */
	public static byte[] asByteArray(String hex) {
		// 文字列長の1/2の長さのバイト配列を生成。
		byte[] bytes = new byte[hex.length() / 2];
		// バイト配列の要素数分、処理を繰り返す。
		for (int index = 0; index < bytes.length; index++) {
			// 16進数文字列をバイトに変換して配列に格納。
			bytes[index] = (byte) Integer.parseInt(
					hex.substring(index * 2, (index + 1) * 2), 16);
		}
		// バイト配列を返す。
		return bytes;
	}

	/**
	 * 半角フィールドの文字列に全角文字が存在した場合に半角文字に変換します。<br>
	 * 前後スペース（全角、半角）削除
	 *
	 * @param value
	 * @return
	 */
	public static String convertHankaku(String value) {
		if (value.length() != 0) {
			String ans = StringConverter.trimDoubleSpace(value);
			ans = ans.trim();
			ans = StringConverter.zenkakuToHankakuSuuji(ans);
			ans = StringConverter.zenkakuToHankakuEikomoji(ans);
			ans = StringConverter.zenkakuToHankakuEioomoji(ans);
			ans = StringConverter.zenkakuToHankakuHiragana(ans);
			ans = StringConverter.zenkakuToHankakuKana(ans);
			ans = StringConverter.zenkakuToHankakuKigou(ans);
			return ans;
		}
		return value;
	}

	/**
	 * 全角フィールドの文字列に半角文字が存在した場合に全角文字に変換します。<br>
	 * 前後スペース（全角、半角）削除
	 *
	 * @param value
	 * @return
	 */
	public static String convertZenkaku(String value) {
		if (value.length() != 0) {
			String ans = StringConverter.trimDoubleSpace(value);
			ans = ans.trim();
			ans = StringConverter.hankakuToZenkakuKana(ans);
			ans = StringConverter.hankakuToZenkakuSuuji(ans);
			ans = StringConverter.hankakuToZenkakuEikomoji(ans);
			ans = StringConverter.hankakuToZenkakuEioomoji(ans);
			ans = StringConverter.hankakuToZenkakuKigou(ans);
			return ans;
		}
		return value;
	}

	/**
	 * 氏名（フリガナ）フィールドの文字列変換で使用。<br>
	 * 以下の手順で行いますが、かな文字（ひらがな、カタカナ）は半角カタカナに一度置き換えてから、再度全角カタカナにします。<br>
	 * <br>
	 * 1.前後スペース（全角、半角）削除。 <br>
	 * 2.全角小文字「ゎ」を「わ」に、「ヮ」を「ワ」に変換します。<br>
	 * 3.ひらがな文字を半角カタカナに変換します。<br>
	 * 4.全角カタカナ文字を半角カタカナに変換します。<br>
	 * 5.半角スペース「 」を全角スペース「　」に、半角長音「ｰ」を全角長音「ー」に、半角カタカナ「ｳﾞ」を全角カタカナ「ヴ」に変換します。<br>
	 * 6.半角カタカナを全角カタカナに変換します。<br>
	 * 7.半角数字を全角数字に変換します。<br>
	 * 8.英小文字（全角、半角）を英大文字（全角、半角）に変換します。<br>
	 * 9.半角英大文字を全角英大文字に変換します。<br>
	 * 10.半角記号を全角記号に変換します。<br>
	 *
	 * @param value
	 * @return
	 */
	public static String convertFurigana(String value) {
		if (value.length() != 0) {
			String ans = StringConverter.trimDoubleSpace(value);
			ans = ans.trim();
			ans = StringConverter.zenkakuToZenkakuSpecialChar(ans);
			ans = StringConverter.zenkakuToHankakuHiragana(ans);
			ans = StringConverter.zenkakuToHankakuKana(ans);
			ans = StringConverter.hankakuToZenkakuFuriganaKigou(ans);
			ans = StringConverter.hankakuToZenkakuKana(ans);
			ans = StringConverter.hankakuToZenkakuSuuji(ans);
			ans = StringConverter.komojiToOomojiEimoji(ans);
			ans = StringConverter.hankakuToZenkakuEioomoji(ans);
			ans = StringConverter.hankakuToZenkakuKigou(ans);
			return ans;
		}
		return value;
	}

	/**
	 * 氏名（フリガナ）フィールドの文字列変換で使用。mobile用<br>
	 * 以下の手順で行います<br>
	 * <br>
	 * 1.前後スペース（全角、半角）削除。 <br>
	 * 2.全角小文字「ゎ」を「わ」に、「ヮ」を「ワ」に変換します。<br>
	 * 3.ひらがな文字を半角カタカナに変換します。<br>
	 * 4.全角カタカナ文字を半角カタカナに変換します。<br>
	 * 5.全角スペース「　」を半角スペース「 」に、全角長音「ー」を半角長音「ｰ」に、全角カタカナ「ヴ」を半角カタカナ「ｳﾞ」に変換します。<br>
	 * 6.全角数字を半角数字に変換します。<br>
	 * 7.英小文字（全角、半角）を英大文字（全角、半角）に変換します。<br>
	 * 8.全角英大文字を半角英大文字に変換します。<br>
	 * 9.全角記号を半角記号に変換します。<br>
	 *
	 * @param value
	 * @return
	 */
	public static String convertFuriganaMobile(String value) {
		if (value.length() != 0) {
			String ans = StringConverter.trimDoubleSpace(value);
			ans = ans.trim();
			ans = StringConverter.zenkakuToZenkakuSpecialChar(ans);
			ans = StringConverter.zenkakuToHankakuHiragana(ans);
			ans = StringConverter.zenkakuToHankakuKana(ans);
			ans = StringConverter.zenkakuToHankakuFuriganaKigou(ans);
			ans = StringConverter.zenkakuToHankakuSuuji(ans);
			ans = StringConverter.komojiToOomojiEimoji(ans);
			ans = StringConverter.zenkakuToHankakuEioomoji(ans);
			ans = StringConverter.zenkakuToHankakuKigou(ans);
			return ans;
		}
		return value;
	}


    /**
     * 入力文字列に半角文字が存在した場合、全角に変換して戻す<br/>
     * 前後スペースは全角半角共にトリミング
     *
     * @param val
     * @return
     */
    public static String toZenkaku(String val) {

        if (null == val || val.length() < 1) {
            return val;
        }

        String ret =
                hankakuToZenkakuKigou(
                        hankakuToZenkakuEioomoji(
                                hankakuToZenkakuEikomoji(
                                        hankakuToZenkakuSuuji(
                                                hankakuToZenkakuKana(
                                                        hankakuToZenkakuFuriganaKigou(val))))));
        return trimDoubleSpace(ret);
    }

    /**
     * 入力文字列に全角文字が存在した場合、半角に変換して戻す<br/>
     * 前後スペースは全角半角共にトリミング
     *
     * @param val
     * @return
     */
    public static String toHankaku(String val) {

        if (null == val || val.length() < 1) {
            return val;
        }

        String ret =
                zenkakuToHankakuKigou(
                        zenkakuToHankakuEioomoji(
                                zenkakuToHankakuEikomoji(
                                        zenkakuToHankakuSuuji(
                                                zenkakuToHankakuKana(
                                                        zenkakuToHankakuHiragana(
                                                                zenkakuToHankakuFuriganaKigou(
                                                                        zenkakuToZenkakuSpecialChar((val)))))))));
        return ret.trim();
    }
}