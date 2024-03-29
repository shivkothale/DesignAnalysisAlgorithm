//Name-Shivkumar Kothale
//BNumber-B01035183

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ConvexHull {

    static class Point {
        int x, y;

        Point(int a, int b) {
            x = a;
            y = b;
        }
    }

    static int orientation(Point p, Point q, Point r) {
        int val = (q.y - p.y) * (r.x - q.x) - (q.x - p.x) * (r.y - q.y);
        if (val == 0) return 0;
        return (val > 0) ? 1 : 2;
    }

    static void convexHull(Point points[], int n) {
        if (n < 3) return;

        Vector<Point> hull = new Vector<Point>();

        // Find the lowest, left-most point
        int l = 0;
        for (int i = 1; i < n; i++)
            if (points[i].y < points[l].y || (points[i].y == points[l].y && points[i].x < points[l].x))
                l = i;

        // Swap the lowest, left-most point with the first point
        Point temp = points[0];
        points[0] = points[l];
        points[l] = temp;

        // Sort the remaining points based on the cross product relative to the corner point
        Arrays.sort(points, 1, n, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                int orientationVal = orientation(points[0], p1, p2);
                if (orientationVal == 0)
                    return (distance(points[0], p2) >= distance(points[0], p1)) ? -1 : 1;
                return (orientationVal == 2) ? -1 : 1;
            }
        });

        int m = 1;
        for (int i = 1; i < n; i++) {
            while (i < n - 1 && orientation(points[0], points[i], points[i + 1]) == 0)
                i++;

            points[m] = points[i];
            m++;
        }

        if (m < 3) return;

        hull.add(points[0]);
        hull.add(points[1]);
        hull.add(points[2]);

        for (int i = 3; i < m; i++) {
            while (orientation(hull.get(hull.size() - 2), hull.get(hull.size() - 1), points[i]) != 2)
                hull.remove(hull.size() - 1);
            hull.add(points[i]);
        }

        System.out.println(hull.size());
        for (int i = 0; i < hull.size(); i++)
            System.out.println( hull.get(i).x + " " + hull.get(i).y);
    }

    static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.x - p1.x, 2) + Math.pow(p2.y - p1.y, 2));
    }

    public static void main(String[] args) {
        try {
            int n;
            String filename = args[0];
            Scanner sc = new Scanner(new File(filename));
            n = Integer.parseInt(sc.next());
            int a, b;
            Point points[] = new Point[n];
            int i = 0;
            while (sc.hasNextLine()) {
                a = Integer.parseInt(sc.next());
                b = Integer.parseInt(sc.next());
                points[i] = new Point(a, b);
                i++;
            }

            convexHull(points, n);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Please enter a file to process on");
            return;
        } catch (FileNotFoundException e) {
            System.out.println("Please give the correct input file");
            return;
        } catch (NoSuchElementException e) {
            System.out.println("Input File is Empty");
            return;
        }
    }
}
