/*
 * Cay S. Horstmann & Gary Cornell, Core Java
 * Published By Sun Microsystems Press/Prentice-Hall
 * Copyright (C) 1997 Sun Microsystems Inc.
 * All Rights Reserved.
 *
 * Permission to use, copy, modify, and distribute this
 * software and its documentation for NON-COMMERCIAL purposes
 * and without fee is hereby granted provided that this
 * copyright notice appears in all copies.
 *
 * THE AUTHORS AND PUBLISHER MAKE NO REPRESENTATIONS OR
 * WARRANTIES ABOUT THE SUITABILITY OF THE SOFTWARE, EITHER
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
 * PARTICULAR PURPOSE, OR NON-INFRINGEMENT. THE AUTHORS
 * AND PUBLISHER SHALL NOT BE LIABLE FOR ANY DAMAGES SUFFERED
 * BY LICENSEE AS A RESULT OF USING, MODIFYING OR DISTRIBUTING
 * THIS SOFTWARE OR ITS DERIVATIVES.
 */

/**
 * A class for formatting numbers that follows printf conventions.
 * Also implements C-like atoi and atof functions
 * @version 1.04 13 Sep 1998
 * @author Cay Horstmann
 */

package com.wasoft.calldb.util;

public class XFormat
{
    /**
     * Formats the number following printf conventions.
     * Main limitation: Can only handle one format parameter at a time
     * Use multiple Format objects to format more than one number
     * @param s the format string following printf conventions
         * The string has a prefix, a format code and a suffix. The prefix and suffix
     * become part of the formatted output. The format code directs the
     * formatting of the (single) parameter to be formatted. The code has the
     * following structure
     * <ul>
     * <li> a % (required)
     * <li> a modifier (optional)
     * <dl>
     * <dt> + <dd> forces display of + for positive numbers
     * <dt> 0 <dd> show leading zeroes
     * <dt> - <dd> align left in the field
     * <dt> space <dd> prepend a space in front of positive numbers
     * <dt> # <dd> use "alternate" format. Add 0 or 0x for octal or hexadecimal numbers. Don't suppress trailing zeroes in general floating point format.
     * </dl>
     * <li> an integer denoting field width (optional)
     * <li> a period followed by an integer denoting precision (optional)
     * <li> a format descriptor (required)
     * <dl>
     * <dt>f <dd> floating point number in fixed format
     * <dt>e, E <dd> floating point number in exponential notation (scientific format). The E format results in an uppercase E for the exponent (1.14130E+003), the e format in a lowercase e.
     * <dt>g, G <dd> floating point number in general format (fixed format for small numbers, exponential format for large numbers). Trailing zeroes are suppressed. The G format results in an uppercase E for the exponent (if any), the g format in a lowercase e.
     * <dt>d, i <dd> integer in decimal
     * <dt>x <dd> integer in hexadecimal
     * <dt>o <dd> integer in octal
     * <dt>s <dd> string
     * <dt>c <dd> character
     * </dl>
     * </ul>
     * @exception IllegalArgumentException if bad format
     */

    public XFormat(String s)
    {
        width = 0;
        precision = -1;
        pre = "";
        post = "";
        leading_zeroes = false;
        show_plus = false;
        alternate = false;
        show_space = false;
        left_align = false;
        fmt = ' ';

        //int state = 0;
        int length = s.length();
        int parse_state = 0;
        // 0 = prefix, 1 = flags, 2 = width, 3 = precision,
        // 4 = format, 5 = end
        int i = 0;

        while (parse_state == 0)
        {
            if (i >= length)
            {
                parse_state = 5;
            }
            else if (s.charAt(i) == '%')
            {
                if (i < length - 1)
                {
                    if (s.charAt(i + 1) == '%')
                    {
                        pre = pre + '%';
                        i++;
                    }
                    else
                    {
                        parse_state = 1;
                    }
                }
                else
                {
                    throw new java.lang.IllegalArgumentException();
                }
            }
            else
            {
                pre = pre + s.charAt(i);
            }
            i++;
        }
        while (parse_state == 1)
        {
            if (i >= length)
            {
                parse_state = 5;
            }
            else if (s.charAt(i) == ' ')
            {
                show_space = true;
            }
            else if (s.charAt(i) == '-')
            {
                left_align = true;
            }
            else if (s.charAt(i) == '+')
            {
                show_plus = true;
            }
            else if (s.charAt(i) == '0')
            {
                leading_zeroes = true;
            }
            else if (s.charAt(i) == '#')
            {
                alternate = true;
            }
            else
            {
                parse_state = 2;
                i--;
            }
            i++;
        }
        while (parse_state == 2)
        {
            if (i >= length)
            {
                parse_state = 5;
            }
            else if ('0' <= s.charAt(i) && s.charAt(i) <= '9')
            {
                width = width * 10 + s.charAt(i) - '0';
                i++;
            }
            else if (s.charAt(i) == '.')
            {
                parse_state = 3;
                precision = 0;
                i++;
            }
            else
            {
                parse_state = 4;
            }
        }
        while (parse_state == 3)
        {
            if (i >= length)
            {
                parse_state = 5;
            }
            else if ('0' <= s.charAt(i) && s.charAt(i) <= '9')
            {
                precision = precision * 10 + s.charAt(i) - '0';
                i++;
            }
            else
            {
                parse_state = 4;
            }
        }
        if (parse_state == 4)
        {
            if (i >= length)
            {
                parse_state = 5;
            }
            else
            {
                fmt = s.charAt(i);
            }
            i++;
        }
        if (i < length)
        {
            post = s.substring(i, length);
        }
    }

    public String toString()
    {
        return getClass().getName();
    }

    /**
     * prints a formatted number following printf conventions
     * @param s a PrintStream
     * @param fmt the format string
     * @param x the double to print
     */

    public static void print(java.io.PrintStream s, String fmt, double x)
    {
        s.print(new XFormat(fmt).form(x));
    }

    /**
     * prints a formatted number following printf conventions
     * @param s a PrintStream
     * @param fmt the format string
     * @param x the long to print
     */
    public static void print(java.io.PrintStream s, String fmt, long x)
    {
        s.print(new XFormat(fmt).form(x));
    }

    /**
     * prints a formatted number following printf conventions
     * @param s a PrintStream
     * @param fmt the format string
     * @param x the character to
     */

    public static void print(java.io.PrintStream s, String fmt, char x)
    {
        s.print(new XFormat(fmt).form(x));
    }

    /**
     * prints a formatted number following printf conventions
     * @param s a PrintStream, fmt the format string
     * @param x a string that represents the digits to print
     */

    public static void print(java.io.PrintStream s, String fmt, String x)
    {
        s.print(new XFormat(fmt).form(x));
    }

    /**
     * Converts a string of digits (decimal, octal or hex) to an integer
     * @param s a string
         * @return the numeric value of the prefix of s representing a base 10 integer
     */

    public static int atoi(String s)
    {
        return (int)atol(s);
    }

    /**
     * Converts a string of digits (decimal, octal or hex) to a long integer
     * @param s a string
         * @return the numeric value of the prefix of s representing a base 10 integer
     */

    public static long atol(String s)
    {
        int i = 0;

        while (i < s.length() && Character.isWhitespace(s.charAt(i)))
        {
            i++;
        }
        if (i < s.length() && s.charAt(i) == '0')
        {
            if (i + 1 < s.length() &&
                (s.charAt(i + 1) == 'x' || s.charAt(i + 1) == 'X'))
            {
                return parseLong(s.substring(i + 2), 16);
            }
            else
            {
                return parseLong(s, 8);
            }
        }
        else
        {
            return parseLong(s, 10);
        }
    }

    private static long parseLong(String s, int base)
    {
        int i = 0;
        int sign = 1;
        long r = 0;

        while (i < s.length() && Character.isWhitespace(s.charAt(i)))
        {
            i++;
        }
        if (i < s.length() && s.charAt(i) == '-')
        {
            sign = -1;
            i++;
        }
        else if (i < s.length() && s.charAt(i) == '+')
        {
            i++;
        }
        while (i < s.length())
        {
            char ch = s.charAt(i);
            if ('0' <= ch && ch < '0' + base)
            {
                r = r * base + ch - '0';
            }
            else if ('A' <= ch && ch < 'A' + base - 10)
            {
                r = r * base + ch - 'A' + 10;
            }
            else if ('a' <= ch && ch < 'a' + base - 10)
            {
                r = r * base + ch - 'a' + 10;
            }
            else
            {
                return r * sign;
            }
            i++;
        }
        return r * sign;
    }

    /**
     * Converts a string of digits to an double
     * @param s a string
     */

    public static double atof(String s)
    {
        int i = 0;
        int sign = 1;
        double r = 0; // integer part
        //double f = 0; // fractional part
        double p = 1; // exponent of fractional part
        int state = 0; // 0 = int part, 1 = frac part

        while (i < s.length() && Character.isWhitespace(s.charAt(i)))
        {
            i++;
        }
        if (i < s.length() && s.charAt(i) == '-')
        {
            sign = -1;
            i++;
        }
        else if (i < s.length() && s.charAt(i) == '+')
        {
            i++;
        }
        while (i < s.length())
        {
            char ch = s.charAt(i);
            if ('0' <= ch && ch <= '9')
            {
                if (state == 0)
                {
                    r = r * 10 + ch - '0';
                }
                else if (state == 1)
                {
                    p = p / 10;
                    r = r + p * (ch - '0');
                }
            }
            else if (ch == '.')
            {
                if (state == 0)
                {
                    state = 1;
                }
                else
                {
                    return sign * r;
                }
            }
            else if (ch == 'e' || ch == 'E')
            {
                long e = (int)parseLong(s.substring(i + 1), 10);
                return sign * r * Math.pow(10, e);
            }
            else
            {
                return sign * r;
            }
            i++;
        }
        return sign * r;
    }

    /**
     * Formats a double into a string (like sprintf in C)
     * @param x the number to format
     * @return the formatted string
     * @exception IllegalArgumentException if bad argument
     */

    public String form(double x)
    {
        String r;
        if (precision < 0)
        {
            precision = 6;
        }
        int s = 1;
        if (x < 0)
        {
            x = -x;
            s = -1;
        }
        if (fmt == 'f')
        {
            r = fixed_format(x);
        }
        else if (fmt == 'e' || fmt == 'E' || fmt == 'g' || fmt == 'G')
        {
            r = exp_format(x);
        }
        else
        {
            throw new java.lang.IllegalArgumentException();
        }

        return pad(sign(s, r));
    }

    /**
     * Formats a long integer into a string (like sprintf in C)
     * @param x the number to format
     * @return the formatted string
     */

    public String form(long x)
    {
        String r;
        int s = 0;
        if (fmt == 'd' || fmt == 'i')
        {
            if (x < 0)
            {
                r = ("" + x).substring(1);
                s = -1;
            }
            else
            {
                r = "" + x;
                s = 1;
            }
        }
        else if (fmt == 'o')
        {
            r = convert(x, 3, 7, "01234567");
        }
        else if (fmt == 'x')
        {
            r = convert(x, 4, 15, "0123456789abcdef");
        }
        else if (fmt == 'X')
        {
            r = convert(x, 4, 15, "0123456789ABCDEF");
        }
        else
        {
            throw new java.lang.IllegalArgumentException();
        }

        return pad(sign(s, r));
    }

    /**
     * Formats a character into a string (like sprintf in C)
     * @param x the value to format
     * @return the formatted string
     */

    public String form(char c)
    {
        if (fmt != 'c')
        {
            throw new java.lang.IllegalArgumentException();
        }

        String r = "" + c;
        return pad(r);
    }

    /**
     * Formats a string into a larger string (like sprintf in C)
     * @param x the value to format
     * @return the formatted string
     */

    public String form(String s)
    {
        if (fmt != 's')
        {
            throw new java.lang.IllegalArgumentException();
        }
        if (precision >= 0 && precision < s.length())
        {
            s = s.substring(0, precision);
        }
        return pad(s);
    }

    private static String repeat(char c, int n)
    {
        if (n <= 0)
        {
            return "";
        }
        StringBuffer s = new StringBuffer(n);
        for (int i = 0; i < n; i++)
        {
            s.append(c);
        }
        return s.toString();
    }

    private static String convert(long x, int n, int m, String d)
    {
        if (x == 0)
        {
            return "0";
        }
        String r = "";
        while (x != 0)
        {
            r = d.charAt( (int) (x & m)) + r;
            x = x >>> n;
        }
        return r;
    }

    private String pad(String r)
    {
        String p = repeat(' ', width - r.length());
        if (left_align)
        {
            return pre + r + p + post;
        }
        else
        {
            return pre + p + r + post;
        }
    }

    private String sign(int s, String r)
    {
        String p = "";
        if (s < 0)
        {
            p = "-";
        }
        else if (s > 0)
        {
            if (show_plus)
            {
                p = "+";
            }
            else if (show_space)
            {
                p = " ";
            }
        }
        else
        {
            if (fmt == 'o' && alternate && r.length() > 0 && r.charAt(0) != '0')
            {
                p = "0";
            }
            else if (fmt == 'x' && alternate)
            {
                p = "0x";
            }
            else if (fmt == 'X' && alternate)
            {
                p = "0X";
            }
        }
        int w = 0;
        if (leading_zeroes)
        {
            w = width;
        }
        else if ( (fmt == 'd' || fmt == 'i' || fmt == 'x' || fmt == 'X' ||
                   fmt == 'o')
                 && precision > 0)
        {
            w = precision;

        }
        return p + repeat('0', w - p.length() - r.length()) + r;
    }

    private String fixed_format(double d)
    {
        boolean removeTrailing
            = (fmt == 'G' || fmt == 'g') && !alternate;
        // remove trailing zeroes and decimal point

        if (d > 0x7FFFFFFFFFFFFFFFL)
        {
            return exp_format(d);
        }
        if (precision == 0)
        {
            /* 原本是这条语句，但下例会出结果”123.44“ 所以修改，另一语句修改原因同此
             *	  return (long) (d + 0.5) + (removeTrailing ? "" : ".");
             *	  XFormat.print(System.out, "|%.2f|\n", 123.445);
             * Xuan 2004.05.19
             */
            return (long) (d + 0.51) + (removeTrailing ? "" : ".");
        }

        long whole = (long)d;
        double fr = d - whole; // fractional part
        if (fr >= 1 || fr < 0)
        {
            return exp_format(d);
        }

        double factor = 1;
        String leading_zeroes = "";
        for (int i = 1; i <= precision && factor <= 0x7FFFFFFFFFFFFFFFL; i++)
        {
            factor *= 10;
            leading_zeroes = leading_zeroes + "0";
        }
        /*
         *	long l = (long) (factor * fr + 0.5);
         */
        long l = (long) (factor * fr + 0.51);
        if (l >= factor)
        {
            l = 0;
            whole++;
        } // CSH 10-25-97

        String z = leading_zeroes + l;
        z = "." + z.substring(z.length() - precision, z.length());

        if (removeTrailing)
        {
            int t = z.length() - 1;
            while (t >= 0 && z.charAt(t) == '0')
            {
                t--;
            }
            if (t >= 0 && z.charAt(t) == '.')
            {
                t--;
            }
            z = z.substring(0, t + 1);
        }

        return whole + z;
    }

    private String exp_format(double d)
    {
        String f = "";
        int e = 0;
        double dd = d;
        double factor = 1;
        if (d != 0)
        {
            while (dd > 10)
            {
                e++;
                factor /= 10;
                dd = dd / 10;
            }
            while (dd < 1)
            {
                e--;
                factor *= 10;
                dd = dd * 10;
            }
        }
        if ( (fmt == 'g' || fmt == 'G') && e >= -4 && e < precision)
        {
            return fixed_format(d);
        }

        d = d * factor;
        f = f + fixed_format(d);

        if (fmt == 'e' || fmt == 'g')
        {
            f = f + "e";
        }
        else
        {
            f = f + "E";

        }
        String p = "000";
        if (e >= 0)
        {
            f = f + "+";
            p = p + e;
        }
        else
        {
            f = f + "-";
            p = p + ( -e);
        }

        return f + p.substring(p.length() - 3, p.length());
    }

//--------------------------------------------------------------------------
    private int width;
    private int precision;
    private String pre;
    private String post;
    private boolean leading_zeroes;
    private boolean show_plus;
    private boolean alternate;
    private boolean show_space;
    private boolean left_align;
    private char fmt; // one of cdeEfgGiosxXos
    //--------------------------------------------------------------------------

    /**
     * a test stub for the format class
     */

    public static void main(String[] a)
    {
        double x = 1.23456789012;
        double y = 123;
        double z = 1.2345e30;
        double w = 1.02;
        double u = 1.234e-5;
        int d = 0xCAFE;
        XFormat.print(System.out, "00 x = |%f|\n", x);
        XFormat.print(System.out, "01 u = |%20f|\n", u);
        XFormat.print(System.out, "02 x = |% .5f|\n", x);
        XFormat.print(System.out, "03 w = |%20.5f|\n", w);
        XFormat.print(System.out, "04 x = |%020.5f|\n", x);
        XFormat.print(System.out, "05 x = |%+20.5f|\n", x);
        XFormat.print(System.out, "06 x = |%+020.5f|\n", x);
        XFormat.print(System.out, "07 x = |% 020.5f|\n", x);
        XFormat.print(System.out, "08 y = |%#+20.5f|\n", y);
        XFormat.print(System.out, "09 y = |%-+20.5f|\n", y);
        XFormat.print(System.out, "10 z = |%20.5f|\n", z);

        XFormat.print(System.out, "11 x = |%e|\n", x);
        XFormat.print(System.out, "12 u = |%20e|\n", u);
        XFormat.print(System.out, "13 x = |% .5e|\n", x);
        XFormat.print(System.out, "14 w = |%20.5e|\n", w);
        XFormat.print(System.out, "15 x = |%020.5e|\n", x);
        XFormat.print(System.out, "16 x = |%+20.5e|\n", x);
        XFormat.print(System.out, "17 x = |%+020.5e|\n", x);
        XFormat.print(System.out, "18 x = |% 020.5e|\n", x);
        XFormat.print(System.out, "19 y = |%#+20.5e|\n", y);
        XFormat.print(System.out, "20 y = |%-+20.5e|\n", y);

        XFormat.print(System.out, "21 x = |%g|\n", x);
        XFormat.print(System.out, "22 z = |%g|\n", z);
        XFormat.print(System.out, "23 w = |%g|\n", w);
        XFormat.print(System.out, "24 u = |%g|\n", u);
        XFormat.print(System.out, "25 y = |%.2g|\n", y);
        XFormat.print(System.out, "26 y = |%#.2g|\n", y);

        XFormat.print(System.out, "27 d = |%d|\n", d);
        XFormat.print(System.out, "28 d = |%20d|\n", d);
        XFormat.print(System.out, "29 d = |%020d|\n", d);
        XFormat.print(System.out, "30 d = |%+20d|\n", d);
        XFormat.print(System.out, "31 d = |% 020d|\n", d);
        XFormat.print(System.out, "32 d = |%-20d|\n", d);
        XFormat.print(System.out, "33 d = |%20.8d|\n", d);
        XFormat.print(System.out, "34 d = |%x|\n", d);
        XFormat.print(System.out, "35 d = |%20X|\n", d);
        XFormat.print(System.out, "36 d = |%#20x|\n", d);
        XFormat.print(System.out, "37 d = |%020X|\n", d);
        XFormat.print(System.out, "38 d = |%20.8x|\n", d);
        XFormat.print(System.out, "39 d = |%o|\n", d);
        XFormat.print(System.out, "40 d = |%020o|\n", d);
        XFormat.print(System.out, "41 d = |%#20o|\n", d);
        XFormat.print(System.out, "42 d = |%#020o|\n", d);
        XFormat.print(System.out, "43 d = |%20.12o|\n", d);

        XFormat.print(System.out, "44 s = |%-20s|\n", "Hello");
        XFormat.print(System.out, "45 s = |%-20c|\n", '!');

        // regression test to confirm fix of reported bugs

        XFormat.print(System.out, "46 |%i|\n", Long.MIN_VALUE);

        XFormat.print(System.out, "47 |%6.2e|\n", 0.0);
        XFormat.print(System.out, "48 |%6.2g|\n", 0.0);

        XFormat.print(System.out, "49 |%6.2f|\n", 9.99);
        XFormat.print(System.out, "50 |%6.2f|\n", 9.999);

        XFormat.print(System.out, "51 |%6.0f|\n", 9.999);

        XFormat.print(System.out, "52 s = |%020s|\n", "Hello");
        XFormat.print(System.out, "53 s = |%020s|\n", "123");
        XFormat.print(System.out, "54 |%.2f|\n", 123.456);
        XFormat.print(System.out, "55 |%.2f|\n", 123.535);
        XFormat.print(System.out, "56 |%.2f|\n", 123.445);
        /*
            00 x = |1.234568|
            01 u = |            0.000012|
            02 x = | 1.23457|
            03 w = |             1.02000|
            04 x = |00000000000001.23457|
            05 x = |            +1.23457|
            06 x = |+0000000000001.23457|
            07 x = | 0000000000001.23457|
            08 y = |          +123.00000|
            09 y = |+123.00000          |
            10 z = |        1.23450E+030|
            11 x = |1.234568e+000|
            12 u = |       1.234000e-005|
            13 x = | 1.23457e+000|
            14 w = |        1.02000e+000|
            15 x = |000000001.23457e+000|
            16 x = |       +1.23457e+000|
            17 x = |+00000001.23457e+000|
            18 x = | 00000001.23457e+000|
            19 y = |       +1.23000e+002|
            20 y = |+1.23000e+002       |
            21 x = |1.234568|
            22 z = |1.2345e+030|
            23 w = |1.02|
            24 u = |1.234e-005|
            25 y = |1.23e+002|
            26 y = |1.23e+002|
            27 d = |51966|
            28 d = |               51966|
            29 d = |00000000000000051966|
            30 d = |              +51966|
            31 d = | 0000000000000051966|
            32 d = |51966               |
            33 d = |            00051966|
            34 d = |cafe|
            35 d = |                CAFE|
            36 d = |              0xcafe|
            37 d = |0000000000000000CAFE|
            38 d = |            0000cafe|
            39 d = |145376|
            40 d = |00000000000000145376|
            41 d = |             0145376|
            42 d = |00000000000000145376|
            43 d = |        000000145376|
            44 s = |Hello               |
            45 s = |!                   |
            46 |-9223372036854775808|
            47 |0.00e+000|
            48 |     0|
            49 |  9.99|
            50 | 10.00|
            51 |   10.|
            52 s = |               Hello|
            53 s = |                 123|
         */

    }
}
