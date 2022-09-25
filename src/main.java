import DG_GUI.GetGraph;
import DG_GUI.Panel;
import Ex2.My_Algo;



public class main {
    public static void main(String[] args) {
        My_Algo a =new My_Algo();
        Panel P=  new Panel(a);


       if(args.length>0){
            GetGraph g= new GetGraph(a,P);
//           String ans =args[0];
//           String ans = "data/G1.json";
             g.loadFromJsom(ans);
      }
    }
}
