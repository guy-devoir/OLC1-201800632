package analizadores;
import java_cup.runtime.Symbol; 


public class Lexico implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 65536;
	private final int YY_EOF = 65537;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private int yychar;
	private int yyline;
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public Lexico (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public Lexico (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Lexico () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yychar = 0;
		yyline = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

    yyline = 1;
    yychar = 1;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		int i;
		for (i = yy_buffer_start; i < yy_buffer_index; ++i) {
			if ('\n' == yy_buffer[i] && !yy_last_was_cr) {
				++yyline;
			}
			if ('\r' == yy_buffer[i]) {
				++yyline;
				yy_last_was_cr=true;
			} else yy_last_was_cr=false;
		}
		yychar = yychar
			+ yy_buffer_index - yy_buffer_start;
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NOT_ACCEPT,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NOT_ACCEPT,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NOT_ACCEPT,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NOT_ACCEPT,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NOT_ACCEPT,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NOT_ACCEPT,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NOT_ACCEPT,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NOT_ACCEPT,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NOT_ACCEPT,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NOT_ACCEPT,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NOT_ACCEPT,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NOT_ACCEPT,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NOT_ACCEPT,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NOT_ACCEPT,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NOT_ACCEPT,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NOT_ACCEPT,
		/* 110 */ YY_NOT_ACCEPT,
		/* 111 */ YY_NOT_ACCEPT,
		/* 112 */ YY_NOT_ACCEPT,
		/* 113 */ YY_NOT_ACCEPT,
		/* 114 */ YY_NOT_ACCEPT,
		/* 115 */ YY_NOT_ACCEPT,
		/* 116 */ YY_NOT_ACCEPT,
		/* 117 */ YY_NOT_ACCEPT,
		/* 118 */ YY_NOT_ACCEPT,
		/* 119 */ YY_NOT_ACCEPT,
		/* 120 */ YY_NOT_ACCEPT,
		/* 121 */ YY_NOT_ACCEPT,
		/* 122 */ YY_NOT_ACCEPT,
		/* 123 */ YY_NOT_ACCEPT,
		/* 124 */ YY_NOT_ACCEPT,
		/* 125 */ YY_NOT_ACCEPT,
		/* 126 */ YY_NOT_ACCEPT,
		/* 127 */ YY_NOT_ACCEPT,
		/* 128 */ YY_NOT_ACCEPT,
		/* 129 */ YY_NOT_ACCEPT,
		/* 130 */ YY_NOT_ACCEPT,
		/* 131 */ YY_NOT_ACCEPT,
		/* 132 */ YY_NOT_ACCEPT,
		/* 133 */ YY_NOT_ACCEPT,
		/* 134 */ YY_NOT_ACCEPT,
		/* 135 */ YY_NOT_ACCEPT,
		/* 136 */ YY_NOT_ACCEPT,
		/* 137 */ YY_NOT_ACCEPT,
		/* 138 */ YY_NOT_ACCEPT,
		/* 139 */ YY_NOT_ACCEPT,
		/* 140 */ YY_NOT_ACCEPT,
		/* 141 */ YY_NOT_ACCEPT,
		/* 142 */ YY_NOT_ACCEPT,
		/* 143 */ YY_NOT_ACCEPT,
		/* 144 */ YY_NOT_ACCEPT,
		/* 145 */ YY_NOT_ACCEPT,
		/* 146 */ YY_NOT_ACCEPT,
		/* 147 */ YY_NOT_ACCEPT,
		/* 148 */ YY_NOT_ACCEPT,
		/* 149 */ YY_NOT_ACCEPT,
		/* 150 */ YY_NOT_ACCEPT,
		/* 151 */ YY_NOT_ACCEPT,
		/* 152 */ YY_NOT_ACCEPT,
		/* 153 */ YY_NOT_ACCEPT,
		/* 154 */ YY_NOT_ACCEPT,
		/* 155 */ YY_NOT_ACCEPT,
		/* 156 */ YY_NOT_ACCEPT,
		/* 157 */ YY_NOT_ACCEPT,
		/* 158 */ YY_NOT_ACCEPT,
		/* 159 */ YY_NOT_ACCEPT,
		/* 160 */ YY_NOT_ACCEPT,
		/* 161 */ YY_NOT_ACCEPT,
		/* 162 */ YY_NOT_ACCEPT,
		/* 163 */ YY_NOT_ACCEPT,
		/* 164 */ YY_NOT_ACCEPT,
		/* 165 */ YY_NOT_ACCEPT,
		/* 166 */ YY_NOT_ACCEPT,
		/* 167 */ YY_NOT_ACCEPT,
		/* 168 */ YY_NOT_ACCEPT,
		/* 169 */ YY_NOT_ACCEPT,
		/* 170 */ YY_NOT_ACCEPT,
		/* 171 */ YY_NOT_ACCEPT,
		/* 172 */ YY_NOT_ACCEPT,
		/* 173 */ YY_NOT_ACCEPT,
		/* 174 */ YY_NOT_ACCEPT,
		/* 175 */ YY_NOT_ACCEPT,
		/* 176 */ YY_NOT_ACCEPT,
		/* 177 */ YY_NOT_ACCEPT,
		/* 178 */ YY_NOT_ACCEPT,
		/* 179 */ YY_NOT_ACCEPT,
		/* 180 */ YY_NOT_ACCEPT,
		/* 181 */ YY_NOT_ACCEPT,
		/* 182 */ YY_NOT_ACCEPT,
		/* 183 */ YY_NOT_ACCEPT,
		/* 184 */ YY_NOT_ACCEPT,
		/* 185 */ YY_NOT_ACCEPT,
		/* 186 */ YY_NOT_ACCEPT,
		/* 187 */ YY_NOT_ACCEPT,
		/* 188 */ YY_NOT_ACCEPT,
		/* 189 */ YY_NOT_ACCEPT,
		/* 190 */ YY_NOT_ACCEPT,
		/* 191 */ YY_NOT_ACCEPT,
		/* 192 */ YY_NOT_ACCEPT,
		/* 193 */ YY_NOT_ACCEPT,
		/* 194 */ YY_NOT_ACCEPT,
		/* 195 */ YY_NOT_ACCEPT,
		/* 196 */ YY_NOT_ACCEPT,
		/* 197 */ YY_NOT_ACCEPT,
		/* 198 */ YY_NOT_ACCEPT,
		/* 199 */ YY_NOT_ACCEPT,
		/* 200 */ YY_NOT_ACCEPT,
		/* 201 */ YY_NOT_ACCEPT,
		/* 202 */ YY_NOT_ACCEPT,
		/* 203 */ YY_NOT_ACCEPT,
		/* 204 */ YY_NOT_ACCEPT,
		/* 205 */ YY_NOT_ACCEPT,
		/* 206 */ YY_NOT_ACCEPT,
		/* 207 */ YY_NOT_ACCEPT,
		/* 208 */ YY_NOT_ACCEPT,
		/* 209 */ YY_NOT_ACCEPT,
		/* 210 */ YY_NOT_ACCEPT,
		/* 211 */ YY_NOT_ACCEPT,
		/* 212 */ YY_NOT_ACCEPT,
		/* 213 */ YY_NOT_ACCEPT,
		/* 214 */ YY_NOT_ACCEPT,
		/* 215 */ YY_NOT_ACCEPT,
		/* 216 */ YY_NOT_ACCEPT,
		/* 217 */ YY_NOT_ACCEPT,
		/* 218 */ YY_NOT_ACCEPT,
		/* 219 */ YY_NOT_ACCEPT,
		/* 220 */ YY_NOT_ACCEPT,
		/* 221 */ YY_NOT_ACCEPT,
		/* 222 */ YY_NOT_ACCEPT,
		/* 223 */ YY_NOT_ACCEPT,
		/* 224 */ YY_NOT_ACCEPT,
		/* 225 */ YY_NOT_ACCEPT,
		/* 226 */ YY_NOT_ACCEPT,
		/* 227 */ YY_NOT_ACCEPT,
		/* 228 */ YY_NOT_ACCEPT,
		/* 229 */ YY_NOT_ACCEPT,
		/* 230 */ YY_NOT_ACCEPT,
		/* 231 */ YY_NOT_ACCEPT,
		/* 232 */ YY_NOT_ACCEPT,
		/* 233 */ YY_NOT_ACCEPT,
		/* 234 */ YY_NOT_ACCEPT,
		/* 235 */ YY_NOT_ACCEPT,
		/* 236 */ YY_NOT_ACCEPT,
		/* 237 */ YY_NOT_ACCEPT,
		/* 238 */ YY_NOT_ACCEPT,
		/* 239 */ YY_NOT_ACCEPT,
		/* 240 */ YY_NOT_ACCEPT,
		/* 241 */ YY_NOT_ACCEPT,
		/* 242 */ YY_NOT_ACCEPT,
		/* 243 */ YY_NOT_ACCEPT,
		/* 244 */ YY_NO_ANCHOR,
		/* 245 */ YY_NO_ANCHOR,
		/* 246 */ YY_NOT_ACCEPT,
		/* 247 */ YY_NOT_ACCEPT,
		/* 248 */ YY_NOT_ACCEPT,
		/* 249 */ YY_NOT_ACCEPT,
		/* 250 */ YY_NOT_ACCEPT,
		/* 251 */ YY_NOT_ACCEPT,
		/* 252 */ YY_NOT_ACCEPT,
		/* 253 */ YY_NOT_ACCEPT,
		/* 254 */ YY_NOT_ACCEPT,
		/* 255 */ YY_NOT_ACCEPT,
		/* 256 */ YY_NOT_ACCEPT,
		/* 257 */ YY_NOT_ACCEPT,
		/* 258 */ YY_NOT_ACCEPT,
		/* 259 */ YY_NOT_ACCEPT,
		/* 260 */ YY_NOT_ACCEPT,
		/* 261 */ YY_NOT_ACCEPT,
		/* 262 */ YY_NOT_ACCEPT,
		/* 263 */ YY_NOT_ACCEPT,
		/* 264 */ YY_NOT_ACCEPT,
		/* 265 */ YY_NOT_ACCEPT,
		/* 266 */ YY_NOT_ACCEPT,
		/* 267 */ YY_NOT_ACCEPT,
		/* 268 */ YY_NOT_ACCEPT,
		/* 269 */ YY_NOT_ACCEPT,
		/* 270 */ YY_NOT_ACCEPT,
		/* 271 */ YY_NOT_ACCEPT,
		/* 272 */ YY_NOT_ACCEPT,
		/* 273 */ YY_NOT_ACCEPT,
		/* 274 */ YY_NOT_ACCEPT,
		/* 275 */ YY_NOT_ACCEPT,
		/* 276 */ YY_NOT_ACCEPT,
		/* 277 */ YY_NOT_ACCEPT,
		/* 278 */ YY_NOT_ACCEPT,
		/* 279 */ YY_NOT_ACCEPT,
		/* 280 */ YY_NOT_ACCEPT,
		/* 281 */ YY_NOT_ACCEPT,
		/* 282 */ YY_NOT_ACCEPT,
		/* 283 */ YY_NOT_ACCEPT,
		/* 284 */ YY_NO_ANCHOR,
		/* 285 */ YY_NOT_ACCEPT,
		/* 286 */ YY_NOT_ACCEPT,
		/* 287 */ YY_NOT_ACCEPT,
		/* 288 */ YY_NOT_ACCEPT,
		/* 289 */ YY_NOT_ACCEPT,
		/* 290 */ YY_NOT_ACCEPT,
		/* 291 */ YY_NOT_ACCEPT,
		/* 292 */ YY_NOT_ACCEPT,
		/* 293 */ YY_NOT_ACCEPT,
		/* 294 */ YY_NOT_ACCEPT,
		/* 295 */ YY_NOT_ACCEPT,
		/* 296 */ YY_NOT_ACCEPT,
		/* 297 */ YY_NOT_ACCEPT,
		/* 298 */ YY_NOT_ACCEPT,
		/* 299 */ YY_NOT_ACCEPT,
		/* 300 */ YY_NOT_ACCEPT,
		/* 301 */ YY_NOT_ACCEPT,
		/* 302 */ YY_NOT_ACCEPT,
		/* 303 */ YY_NOT_ACCEPT,
		/* 304 */ YY_NOT_ACCEPT,
		/* 305 */ YY_NOT_ACCEPT,
		/* 306 */ YY_NOT_ACCEPT,
		/* 307 */ YY_NOT_ACCEPT,
		/* 308 */ YY_NOT_ACCEPT,
		/* 309 */ YY_NOT_ACCEPT,
		/* 310 */ YY_NOT_ACCEPT,
		/* 311 */ YY_NOT_ACCEPT,
		/* 312 */ YY_NOT_ACCEPT,
		/* 313 */ YY_NOT_ACCEPT,
		/* 314 */ YY_NOT_ACCEPT,
		/* 315 */ YY_NOT_ACCEPT,
		/* 316 */ YY_NOT_ACCEPT,
		/* 317 */ YY_NOT_ACCEPT,
		/* 318 */ YY_NOT_ACCEPT,
		/* 319 */ YY_NOT_ACCEPT,
		/* 320 */ YY_NOT_ACCEPT,
		/* 321 */ YY_NOT_ACCEPT,
		/* 322 */ YY_NOT_ACCEPT,
		/* 323 */ YY_NOT_ACCEPT,
		/* 324 */ YY_NOT_ACCEPT,
		/* 325 */ YY_NOT_ACCEPT,
		/* 326 */ YY_NOT_ACCEPT,
		/* 327 */ YY_NOT_ACCEPT,
		/* 328 */ YY_NOT_ACCEPT,
		/* 329 */ YY_NOT_ACCEPT
	};
	private int yy_cmap[] = unpackFromString(1,65538,
"55:10,2,55:2,1,55:18,52,50,6,55,10,55:2,9,35,36,31,29,44,30,4,32,3:10,55,45" +
",37,40,38,50,55,21,23,20,22,17,25,42,51,34,54,7,24,16,13,19,33,53,18,26,28," +
"43,27,7:2,39,7,46,8,47,55,41,55,21,23,20,22,17,25,42,51,34,54,7,24,16,13,19" +
",33,53,18,26,28,43,27,7:2,39,7,11,5,12,55:35,49,55:24,15,55:4,49,55:2,48,14" +
",55:30,48,14,55:65308,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,330,
"0,1,2,3,1:2,4,1,5,1:4,6,1:5,7,1:5,8,1:3,9,10,1:3,11,12,1:3,13,1:6,14,1:11,1" +
"5,1:15,8,1,16,1,17,18,19,20,1,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35," +
"36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,10,57,58,59," +
"60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84," +
"85,86,87,88,89,90,91,92,93,94,95,96,97,98,99,100,101,102,103,104,105,106,10" +
"7,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,1" +
"26,127,128,129,130,131,132,133,134,135,136,137,138,139,140,141,142,143,144," +
"145,146,147,148,149,150,151,152,153,154,155,156,157,158,159,160,161,162,163" +
",164,165,166,167,168,169,170,171,172,173,174,175,176,177,178,179,180,181,18" +
"2,183,184,185,186,187,188,189,190,191,192,193,194,195,196,197,198,199,200,2" +
"01,202,203,204,205,206,207,208,209,210,211,212,213,214,215,216,217,218,219," +
"220,221,222,223,224,225,226,227,228,229,230,231,232,233,234,235,236,237,238" +
",239,240,241,242,243,244,245,246,247,248,249,250,251,252,253,254,255,256,25" +
"7,258,259,260,261,262,263,264,265,266")[0];

	private int yy_nxt[][] = unpackFromString(267,56,
"1,2,75,3,4:2,76,4,81,4:4,84,4:2,86,88,90,92,94,4,244,96,4,98,100,284,4,5,6," +
"7,8,245,102,9,10,11,12,4,13,104,4:2,14,15,16,17,106,4,18,108,4:4,-1:58,80,-" +
"1:56,3,74,-1:89,77,-1:48,21,-1:61,22,23,-1:23,26,78,-1:5,78,-1:2,78:13,-1:4" +
",78:2,-1:4,78,-1,78:3,-1:7,78,-1,78:2,-1:4,25,-1,25,-1:46,25,-1:44,154,-1:1" +
"7,119,-1:3,119,-1:5,119,-1:2,119:13,-1:4,119:2,-1:4,119,-1,30,119:2,-1:7,11" +
"9,-1,119:2,-1:42,268,-1:55,325,-1:55,189,-1:35,54,-1:75,222,-1:15,79,83:2,-" +
"1:3,19,-1:2,85,-1:2,19,-1:2,19:13,-1:4,19:2,-1:4,19,-1,19:3,-1:7,19,-1,19:2" +
",-1:8,78,-1:5,78,-1:2,78:13,-1:4,78:2,-1:4,78,-1,78:3,-1:7,78,-1,78:2,-1:3," +
"83,-1:3,26,-1:50,121,122,123,-1:3,124,-1:5,124,-1:2,124:13,-1:4,124:2,-1:4," +
"124,-1,124:3,-1:7,124,-1,124:2,-1:10,87,-1:52,26,-1:63,89,-1:4,91,-1:47,125" +
",-1:61,93,-1,95,-1,97,-1:12,99,-1:22,126,127:2,-1:3,127,-1:2,85,-1:2,127,-1" +
":2,127:13,-1:4,127:2,-1:4,127,-1,127:3,-1:7,127,-1,127:2,-1:14,101,-1:12,10" +
"3,-1:27,246,-1:16,128,-1:57,105,-1:66,27,-1:68,107,-1:27,249,-1:14,285,-1:4" +
"6,109,-1,110,-1:56,28,-1:52,111,-1:75,299,-1:37,112,-1:12,113,-1:8,114,-1:2" +
"9,251,-1:55,115,-1:16,20,-1:49,306,-1:40,117,-1:2,118,-1:80,129,-1:21,119,-" +
"1:5,119,-1:2,119:13,-1:4,119:2,-1:4,119,-1,119:3,-1:7,119,-1,119:2,-1:29,31" +
"1,-1:4,131,-1:71,24,-1:32,132,-1:50,120,-1:47,133,-1:2,134,-1:57,135,-1:3,2" +
"54,-1:52,286,-1:60,136,-1:44,29,-1:55,253,-1:84,137,-1:31,138,-1:71,316,-1:" +
"7,140,-1:46,257,-1:42,287,-1:5,141,-1:31,122,-1:3,82,-1:55,82,-1:52,123,-1:" +
"2,82,-1:55,82,124,-1:5,124,-1:2,124:13,-1:4,124:2,-1:4,124,-1,124:3,-1:7,12" +
"4,-1,124:2,-1:4,142,-1:54,127,-1:5,143,-1:55,143,-1:63,300,-1:61,147,-1:11," +
"148,-1:41,255,-1:52,289,-1:72,31,-1:62,149,-1:10,260,-1:22,32,-1:57,150,-1:" +
"60,153,-1:72,155,-1:34,156,-1:54,33,-1:52,262,-1:65,158,-1:30,142,-1:8,159," +
"-1:52,26,-1:64,34,-1:55,35,-1:50,261,-1:76,162,-1:63,163,-1:40,263,-1:5,292" +
",267,-1:41,266,-1:54,168,-1:53,302,-1:57,36,-1:52,169,-1:8,265,170,-1:6,308" +
",-1:35,37,-1:63,172,-1:52,38,-1:58,39,-1:40,26,-1,143,-1:65,177,-1:56,40,-1" +
":61,271,-1:73,270,-1:40,294,-1:40,303,-1:76,180,-1:42,41,-1:75,293,-1:31,18" +
"4,-1:16,304,-1:38,186,-1:16,42,-1:40,187,-1:58,310,-1:53,188,-1:54,43,-1:62" +
",309,-1:45,274,-1:58,44,-1:57,191,-1:51,192,-1:56,45,-1:61,197,-1:49,198,-1" +
":50,46,-1:70,296,-1:40,201,-1:84,202,-1:26,47,-1:76,204,-1:74,277,-1:43,207" +
",-1:40,48,-1:55,49,-1:46,312,-1:62,50,-1:49,51,-1:55,52,-1:56,208,-1:57,209" +
",-1:52,210,-1:55,53,-1:57,278,-1:78,214,-1:33,55,-1:55,56,-1:52,57,-1:55,58" +
",-1:71,217,-1:39,59,-1:53,297,-1:56,218,-1:51,318,-1:64,219,-1:61,220,-1:40" +
",60,-1:61,61,-1:53,62,-1:80,223,-1:29,225,-1:58,63,-1:54,226,-1:56,227,-1:4" +
"9,228,-1:85,282,-1:40,229,-1:44,230,-1:59,231,-1:47,64,-1:66,65,-1:49,235,-" +
"1:50,236,-1:68,66,-1:53,67,-1:55,68,-1:48,69,-1:57,238,-1:64,239,-1:45,240," +
"-1:63,70,-1:50,241,-1:68,243,-1:45,71,-1:55,72,-1:50,73,-1:53,248,-1:57,247" +
",-1,252,-1:51,130,-1:66,317,-1:68,250,-1:33,144,-1:60,151,-1:44,258,-1:60,1" +
"39,-1:57,291,-1:52,259,-1:81,164,-1:34,161,-1:51,307,-1:65,264,-1:40,167,-1" +
":76,329,-1:41,179,-1:52,175,-1:59,181,-1:52,178,-1:80,185,-1:40,295,-1:40,2" +
"69,-1:61,190,-1:56,199,-1:56,194,-1:51,193,-1:56,203,-1:50,213,-1:76,206,-1" +
":40,211,-1:54,215,-1:80,216,-1:46,221,-1:38,280,-1:51,281,-1:70,234,-1:48,2" +
"32,-1:55,242,-1:51,116,-1:57,256,-1:60,152,-1:48,157,-1:56,165,-1:65,166,-1" +
":40,173,-1:76,171,-1:42,182,-1:54,275,-1:56,195,-1:51,200,-1:57,212,-1:53,2" +
"24,-1:59,233,-1:53,145,-1:53,160,-1:72,174,-1:42,183,-1:55,196,-1:51,273,-1" +
":59,237,-1:53,146,-1:70,176,-1:42,272,-1:55,205,-1:51,276,-1:57,288,-1:54,2" +
"79,-1:55,305,-1:80,298,-1:40,283,-1:47,301,-1:52,290,-1:66,313,-1:69,314,-1" +
":26,315,-1:76,319,-1:38,320,-1:79,321,-1:30,322,-1:58,323,-1:53,324,-1:56,3" +
"26,-1:57,327,-1:48,328,-1:42");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{ /* ignore */ }
					case -3:
						break;
					case 3:
						{ return new Symbol(sym.NUMERO,yyline,yychar,yytext()); }
					case -4:
						break;
					case 4:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -5:
						break;
					case 5:
						{ return new Symbol(sym.SUMA,yyline,yychar,yytext()); }
					case -6:
						break;
					case 6:
						{ return new Symbol(sym.RESTA,yyline,yychar,yytext()); }
					case -7:
						break;
					case 7:
						{ return new Symbol(sym.MULT,yyline,yychar,yytext()); }
					case -8:
						break;
					case 8:
						{ return new Symbol(sym.DIVD,yyline,yychar,yytext()); }
					case -9:
						break;
					case 9:
						{ return new Symbol(sym.PARENABRE,yyline,yychar,yytext()); }
					case -10:
						break;
					case 10:
						{ return new Symbol(sym.PARENCIERRA,yyline,yychar,yytext()); }
					case -11:
						break;
					case 11:
						{ return new Symbol(sym.MENORQUE,yyline,yychar,yytext()); }
					case -12:
						break;
					case 12:
						{ return new Symbol(sym.MAYORQUE,yyline,yychar,yytext()); }
					case -13:
						break;
					case 13:
						{return new Symbol(sym.ASSIGN,yyline,yychar,yytext());}
					case -14:
						break;
					case 14:
						{ return new Symbol(sym.COMA,yyline,yychar,yytext()); }
					case -15:
						break;
					case 15:
						{ return new Symbol(sym.PUNTOCOMA,yyline,yychar,yytext()); }
					case -16:
						break;
					case 16:
						{ return new Symbol(sym.CORCABRE,yyline,yychar,yytext()); }
					case -17:
						break;
					case 17:
						{ return new Symbol(sym.CORCCIERRA,yyline,yychar,yytext()); }
					case -18:
						break;
					case 18:
						{ return new Symbol(sym.CLOSECASE,yyline,yychar,yytext()); }
					case -19:
						break;
					case 19:
						{ return new Symbol(sym.STRING_CONTENT,yyline,yychar,yytext()); }
					case -20:
						break;
					case 20:
						{ return new Symbol(sym.IF,yyline,yychar,yytext()); }
					case -21:
						break;
					case 21:
						{ /* ignore */ }
					case -22:
						break;
					case 22:
						{ return new Symbol(sym.MENORIGUAL,yyline,yychar,yytext()); }
					case -23:
						break;
					case 23:
						{ return new Symbol(sym.MAYORIGUAL,yyline,yychar,yytext()); }
					case -24:
						break;
					case 24:
						{ return new Symbol(sym.OPENCASE,yyline,yychar,yytext()); }
					case -25:
						break;
					case 25:
						{ return new Symbol(sym.DECIMAL,yyline,yychar,yytext()); }
					case -26:
						break;
					case 26:
						{ return new Symbol(sym.CHAR_CONTENT,yyline,yychar,yytext()); }
					case -27:
						break;
					case 27:
						{ return new Symbol(sym.INVERSE,yyline,yychar,yytext());}
					case -28:
						break;
					case 28:
						{ return new Symbol(sym.MODULO,yyline,yychar,yytext()); }
					case -29:
						break;
					case 29:
						{ return new Symbol(sym.FIN,yyline,yychar,yytext()); }
					case -30:
						break;
					case 30:
						{return new Symbol(sym.IDENTIFICADOR,yyline,yychar,yytext());}
					case -31:
						break;
					case 31:
						{ return new Symbol(sym.ELIF,yyline,yychar,yytext()); }
					case -32:
						break;
					case 32:
						{ return new Symbol(sym.COMO,yyline,yychar,yytext()); }
					case -33:
						break;
					case 33:
						{ return new Symbol(sym.FOR,yyline,yychar,yytext()); }
					case -34:
						break;
					case 34:
						{ return new Symbol(sym.MENORQUE,yyline,yychar,yytext()); }
					case -35:
						break;
					case 35:
						{ return new Symbol(sym.MAYORQUE,yyline,yychar,yytext()); }
					case -36:
						break;
					case 36:
						{ return new Symbol(sym.FALSE,yyline,yychar,yytext());}
					case -37:
						break;
					case 37:
						{ return new Symbol(sym.SWITCH,yyline,yychar,yytext()); }
					case -38:
						break;
					case 38:
						{ return new Symbol(sym.DO,yyline,yychar,yytext()); }
					case -39:
						break;
					case 39:
						{ return new Symbol(sym.UNTIL,yyline,yychar,yytext()); }
					case -40:
						break;
					case 40:
						{ return new Symbol(sym.METOD,yyline,yychar,yytext()); }
					case -41:
						break;
					case 41:
						{ return new Symbol(sym.STRING,yyline,yychar,yytext());}
					case -42:
						break;
					case 42:
						{ return new Symbol(sym.IFCLOSE,yyline,yychar,yytext()); }
					case -43:
						break;
					case 43:
						{ return new Symbol(sym.INICIO,yyline,yychar,yytext()); }
					case -44:
						break;
					case 44:
						{ return new Symbol(sym.INTVALUE,yyline,yychar,yytext());}
					case -45:
						break;
					case 45:
						{ return new Symbol(sym.REPEAT,yyline,yychar,yytext()); }
					case -46:
						break;
					case 46:
						{ return new Symbol(sym.BOOLEAN,yyline,yychar,yytext());}
					case -47:
						break;
					case 47:
						{ return new Symbol(sym.FUN,yyline,yychar,yytext()); }
					case -48:
						break;
					case 48:
						{ return new Symbol(sym.WHILE,yyline,yychar,yytext()); }
					case -49:
						break;
					case 49:
						{ return new Symbol(sym.SO,yyline,yychar,yytext()); }
					case -50:
						break;
					case 50:
						{ return new Symbol(sym.ESIGUAL,yyline,yychar,yytext()); }
					case -51:
						break;
					case 51:
						{ return new Symbol(sym.EXECUTE,yyline,yychar,yytext()); }
					case -52:
						break;
					case 52:
						{ return new Symbol(sym.RETURN,yyline,yychar,yytext()); }
					case -53:
						break;
					case 53:
						{ return new Symbol(sym.CHAR,yyline,yychar,yytext());}
					case -54:
						break;
					case 54:
						{ return new Symbol(sym.BOOLEAN,yyline,yychar,yytext());}
					case -55:
						break;
					case 55:
						{ return new Symbol(sym.FORCLOSE,yyline,yychar,yytext()); }
					case -56:
						break;
					case 56:
						{ return new Symbol(sym.POTENCIA,yyline,yychar,yytext()); }
					case -57:
						break;
					case 57:
						{ return new Symbol(sym.INGRESAR,yyline,yychar,yytext()); }
					case -58:
						break;
					case 58:
						{ return new Symbol(sym.PRINT,yyline,yychar,yytext()); }
					case -59:
						break;
					case 59:
						{ return new Symbol(sym.CON_VALOR,yyline,yychar,yytext()); }
					case -60:
						break;
					case 60:
						{ return new Symbol(sym.SWITCHCLOSE,yyline,yychar,yytext()); }
					case -61:
						break;
					case 61:
						{ return new Symbol(sym.TRUE,yyline,yychar,yytext()); }
					case -62:
						break;
					case 62:
						{ return new Symbol(sym.UNTIL_CONDITION,yyline,yychar,yytext()); }
					case -63:
						break;
					case 63:
						{ return new Symbol(sym.METODCLOSE,yyline,yychar,yytext()); }
					case -64:
						break;
					case 64:
						{ return new Symbol(sym.FUNCLOSE,yyline,yychar,yytext()); }
					case -65:
						break;
					case 65:
						{ return new Symbol(sym.PRINTLN,yyline,yychar,yytext()); }
					case -66:
						break;
					case 66:
						{ return new Symbol(sym.WHILECLOSE,yyline,yychar,yytext()); }
					case -67:
						break;
					case 67:
						{ return new Symbol(sym.MENORIGUAL,yyline,yychar,yytext()); }
					case -68:
						break;
					case 68:
						{ return new Symbol(sym.MAYORIGUAL,yyline,yychar,yytext()); }
					case -69:
						break;
					case 69:
						{ return new Symbol(sym.NOESIGUAL,yyline,yychar,yytext()); }
					case -70:
						break;
					case 70:
						{ return new Symbol(sym.PARAMETERS,yyline,yychar,yytext()); }
					case -71:
						break;
					case 71:
						{ return new Symbol(sym.INCREMENT,yyline,yychar,yytext()); }
					case -72:
						break;
					case 72:
						{ return new Symbol(sym.INCREMENT,yyline,yychar,yytext()); }
					case -73:
						break;
					case 73:
						{ return new Symbol(sym.ELSE,yyline,yychar,yytext()); }
					case -74:
						break;
					case 75:
						{ /* ignore */ }
					case -75:
						break;
					case 76:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -76:
						break;
					case 77:
						{return new Symbol(sym.ASSIGN,yyline,yychar,yytext());}
					case -77:
						break;
					case 78:
						{ return new Symbol(sym.STRING_CONTENT,yyline,yychar,yytext()); }
					case -78:
						break;
					case 80:
						{ /* ignore */ }
					case -79:
						break;
					case 81:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -80:
						break;
					case 82:
						{ return new Symbol(sym.STRING_CONTENT,yyline,yychar,yytext()); }
					case -81:
						break;
					case 84:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -82:
						break;
					case 86:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -83:
						break;
					case 88:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -84:
						break;
					case 90:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -85:
						break;
					case 92:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -86:
						break;
					case 94:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -87:
						break;
					case 96:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -88:
						break;
					case 98:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -89:
						break;
					case 100:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -90:
						break;
					case 102:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -91:
						break;
					case 104:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -92:
						break;
					case 106:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -93:
						break;
					case 108:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -94:
						break;
					case 244:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -95:
						break;
					case 245:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -96:
						break;
					case 284:
						{ 
    System.out.println(yytext() + "Error Lexico en linea: " + yyline + " y columna: " + yychar);
}
					case -97:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
