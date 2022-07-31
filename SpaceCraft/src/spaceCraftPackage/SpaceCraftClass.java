package spaceCraftPackage;

import java.util.Scanner;

public class SpaceCraftClass {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Input:");
		String input1 = scan.nextLine();// getting the first line of input
		input1 += "_";// for final meteorites velocity
		double[] y1 = new double[input1.length()];
		int[] y1x = new int[input1.length()];
		double[] v1 = new double[input1.length() / 3];

		int u = 0, s = 0, z = 0, w = 0;
		String str = "";
		// split the string & convert to a numbers
		for (int i = 0; i < input1.length(); i++) {
			if (input1.charAt(i) == '_') { // meteorites velocity
				v1[u] = Double.parseDouble(str);// string -> double
				u++;
				str = "";
			} else if (input1.charAt(i) == '(' && s == 0) {// the number of meteorites
				z = Integer.parseInt(str);// string -> int
				str = "";
			} else if (input1.charAt(i) == ',')
				str = "";// ignore ,
			else if (input1.charAt(i) == ')') {// ordinate meteorites /width of meteors
				// if int example == double example -> example is integer
				int iy1 = Integer.parseInt(str);
				double dy1 = Double.parseDouble(str);
				if (iy1 == dy1) {
					y1x[w] = s;// save index in the y1x array
					w++;
				}
				y1[s] = dy1;
				s++;
				str = "";
			} else if (input1.charAt(i) == ' ' || input1.charAt(i) == '(')
				continue;// skip space & (
			else
				str += input1.charAt(i);
		}

		String input2 = scan.nextLine();// getting the second line input
		System.out.println();
		input2 += "_";// for final space craft velocity
		str = "";
		int[] x2 = new int[input2.length() / 2];
		double[] v2 = new double[input2.length() / 2];

		int n = 0, m = 0;
		for (int i = 0; i < input2.length(); i++) {// separating string & convert to numbers
			if (input2.charAt(i) == '-') {// velocity change abscissa
				x2[m] = Integer.parseInt(str);
				m++;
				str = "";
			} else if (input2.charAt(i) == '_') {// space craft velocity
				v2[n] = Double.parseDouble(str);
				n++;
				str = "";
			} else if (input2.charAt(i) == ' ')// skip space
				continue;
			else
				str += input2.charAt(i);
		}

		double t2 = 0;
		int j = 0, e = 1;
		double[] time = new double[z + 1];
		int[] timex = new int[z + 1];
		time[0] = 0;
		timex[0] = 0;

		if (x2[m - 1] < z)
			x2[m] = z;

		for (int i = 1; i <= z; i++) {// Tf = 1/v + Tb
			if (i > x2[j])
				j++;// velocity range
			double t1 = 1.0 / v2[j - 1];
			t2 += t1;
			int t2x = (int) t2;
			if (t2x == t2) {// find integer
				timex[e] = i;// save index in timex array
				e++;
			}
			time[i] = t2;
		}

		for (int i = 1; i <= z; i++)// y=y0-vt
			for (int k = 0; k < z; k++) {
				double d = y1[k] - v1[k] * time[i];
				y1[s] = (double) Math.round(d * 100) / 100;// rounding up to 2 decimal places
				int iy1s = (int) y1[s];
				if (iy1s == y1[s]) {// find integer
					y1x[w] = s;// save index in y1x array
					w++;
				}
				s++;
			}

		System.out.println("Output:");
		int q = 0, situation = 1, p = 0, l = 0;
		for (int i = 0; i <= z; i++) {
			if (i == timex[l]) {// turn to int
				System.out.print("t=" + (int) time[i] + "  " + "R=(" + i + ",0)  ");
				l++;
			} else
				System.out.print("t=" + time[i] + "  " + "R=(" + i + ",0)  ");

			for (int b = 1; b <= z; b++) {// Mi

				if (q == y1x[p]) {// turn to int
					System.out.print("M" + b + "=(" + b + "," + (int) y1[q] + ")  ");
					p++;
				} else
					System.out.print("M" + b + "=(" + b + "," + y1[q] + ")  ");

				if (i == b && y1[q] == 0)// about crash
					situation = 0;

				q++;
			}
			if (situation == 1)
				System.out.print("safe");
			else
				System.out.print("!CRASHED!");
			System.out.println();
		}

		scan.close();
	}
}
