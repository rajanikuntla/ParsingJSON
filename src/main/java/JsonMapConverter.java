import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.util.*;

public class JsonMapConverter {

    //This method adds the structure obtects in given unformatted json to map
    public static void addToList(JSONObject object){
        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            Object value = object.get(keysItr.next());

            if(value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            } else if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
        }
    }

    public static List<Object> toList(JSONArray array) {
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.size(); i++) {
            Object value = array.get(i);
            if(value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }

            else if(value instanceof JSONObject) {
                Structure struct = toMap((JSONObject) value);
                ParseStructureMain.structList.put(struct.getId(), struct);
            }
            list.add(value);
        }
        return list;
    }

    public static Structure toMap(JSONObject object){
        Structure temp = new Structure();
        Iterator<String> keysItr = object.keySet().iterator();
        while(keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);
            if(key.equals("title")){
                temp.setTitle((String)value);
            } else if(key.equals("id")){
                temp.setId((Long)value);
            } else if(key.equals("parent_id")){
                if(value == null)
                    temp.setParent_id(Long.valueOf(-1));
                else
                    temp.setParent_id((Long)value);
            } else if(key.equals("level")){
                temp.setLevel((Long)value);
            }
        }
        if(temp.getLevel() == 0){
            ParseStructureMain.root = temp.getId();
        }
        return temp;
    }


    //transforms the map to the required format
    public static Structure transformJson(Map<Long, Structure> structList){
        Structure result;
        result = structList.get(ParseStructureMain.root);
        addChildren(result, result.getId());
        return result;
    }

    //adds the children to the result object
    private static void addChildren(Structure parent, Long parentId) {

        Stack<Long> idTrack = new Stack<Long>();
        ArrayList<Structure> children = new ArrayList<Structure>();
        Iterator<Long> iterator = ParseStructureMain.structList.keySet().iterator();
        while(iterator.hasNext()){
           Structure temp = ParseStructureMain.structList.get(iterator.next());
           if(temp.getParent_id() == parentId){
               idTrack.push(temp.getId());
               children.add(temp);
           }
        }
        parent.setChildren(children);
        while(!idTrack.empty()){
            Long tempId = idTrack.pop();
            int tempInt = checkId(children, tempId);
            addChildren(parent.getChildren().get(tempInt), tempId);
        }


    }

    //checks for the particular id in the children
    public static int checkId(ArrayList<Structure> children,Long id){
        int i = 0;
        for(Structure child : children){
            if(child.getId() ==  id)
                return i ;
            else
                ++i;
        }
       return -1;
    }
}
