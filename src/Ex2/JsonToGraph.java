package Ex2;

import api.NodeData;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map.Entry;


public class JsonToGraph implements JsonDeserializer<Graph> {
    @Override
    public Graph deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        HashMap<Integer, NodeData> n = new HashMap<>();
        HashMap<NodeData, HashMap> e = new HashMap<>();
        Graph g = new Graph(n, e);

        JsonArray nodesJson = jsonObject.get("Nodes").getAsJsonArray();
        JsonArray edgesJson = jsonObject.get("Edges").getAsJsonArray();
        for (JsonElement node : nodesJson) {
            int key = node.getAsJsonObject().get("id").getAsInt();
            String[] pos = node.getAsJsonObject().get("pos").getAsString().split(",");
            Location location = new Location(Double.parseDouble(pos[0]), Double.parseDouble(pos[1]), Double.parseDouble(pos[2]));
            g.addNode(new Node(key, location));

        }
        for (JsonElement edge : edgesJson) {
                int src = edge.getAsJsonObject().get("src").getAsInt();
                int dest = edge.getAsJsonObject().get("dest").getAsInt();
                double w = edge.getAsJsonObject().get("w").getAsDouble();
                g.connect(src, dest, w);
            }

        return g;
    }

}
