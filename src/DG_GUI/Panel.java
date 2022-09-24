package DG_GUI;

import Ex2.Graph;

import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;


    public class Panel extends JPanel {
    DirectedWeightedGraphAlgorithms alg;
    LinkedList<GeoLocation> points = new LinkedList<GeoLocation>();

    Graph graph;
    HashMap<Integer, NodeData> nodes;

    double Xmin;
    double Xmax;
    double Ymin;
    double Ymax;

    double absX;
    double absY;
    double scaleX;
    double scaleY;

    int src;
    int dest;
    GeoLocation srcL;
    GeoLocation destL;
    double srcX;
    double srcY;
    double destX;
    double destY;

    public Panel(DirectedWeightedGraphAlgorithms alg) {
        this.alg = alg;
        this.setBackground(Color.lightGray);
        this.setPreferredSize(new Dimension(1350, 600));

        graph = (Graph) this.alg.getGraph();
        nodes = graph.getNodes();

        boolean in = true;
        for (NodeData n : nodes.values()) {
            GeoLocation l = n.getLocation();
            if (in == true) {
                Xmin = l.x();
                Xmax = l.x();
                Ymin = l.y();
                Ymax = l.y();
            }
            in = false;
            if (l.x() < Xmin) {
                Xmin = l.x();
            } else if (l.x() > Xmax) {
                Xmax = l.x();
            }

            if (l.y() < Ymin) {
                Ymin = l.y();
            } else if (l.y() > Ymax) {
                Ymax = l.y();
            }
        }

        absX = Math.abs(Xmax - Xmin);
        absY = Math.abs(Ymax - Ymin);

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBounds((int) 110, (int) 20, (int) absX, (int) absY);

        scaleX = screen.getWidth() / absX * 0.6;
        scaleY = screen.getHeight() / absY * 0.6;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(3));
        g2.setColor(Color.BLUE);

        for(NodeData n : nodes.values()) {
            GeoLocation location = n.getLocation();
            double posX = (location.x() - Xmin) * scaleX + 12;
            double posY = (location.y() - Ymin) * scaleY + 12;

            g2.fillOval((int) posX - 5, (int) posY - 5, 12, 12);
        }
        Iterator<EdgeData> edges = this.graph.edgeIter();

        while (edges.hasNext()) {
            EdgeData e = edges.next();
            src = e.getSrc();
            dest = e.getDest();
            srcL = nodes.get(src).getLocation();
            destL = nodes.get(dest).getLocation();

            srcX = (srcL.x() - Xmin) * scaleX + 12;
            srcY = (srcL.y() - Ymin) * scaleY + 12;
            destX = (destL.x() - Xmin) * scaleX + 12;
            destY = (destL.y() - Ymin) * scaleY + 12;
            g.setColor(Color.red);
            g2.drawLine((int) srcX, (int) srcY, (int) destX, (int) destY);

            int dx = (int)(destX-srcX);
            int dy = (int)(destY-srcY);
            double d = Math.sqrt(dx*dx+ dy*dy);
            double xm = d-10;
            double xn = xm;
            double ym = 10;
            double yn = -10,x;
            double sin = dy/d,cos=dx/d;
            x = xm*cos - ym*sin + srcX;
            ym = xm*sin + ym*cos + srcY;
            xm = x;
            x = xn*cos - yn*sin + srcX;
            yn = xn*sin + yn*cos + srcY;
            xn = x;
            int[] xpoints = {(int)destX, (int) xm, (int) xn};
            int[] ypoints = {(int)destY, (int) ym, (int) yn};
            g.setColor(Color.red);
            g.fillPolygon(xpoints,ypoints,3);


        }


    }

}





