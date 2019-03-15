public class ParseMain {

    public static void main(String[] args){

        try {

            String input ="---\n"+
                    "title: \"What Are Annotations?\"\n" +
                    "description: \"Annotations allow users to add custom content on PDF pages.\"\n"
                    +"preview_image: /images/blog/2018/what-are-annotations/article-header.png\n"
                    +"section: blog\n"+
                    "---\n" +
                    "This post will discuss what PDF annotations are, exploring which types of annotations are defined by the PDF specification\n" +
                    "READMORE\n"+
                    "![Annotations](/images/blog/2018/what-are-annotations/article-header.png)\n" ;


            String jsonString = "{";
            boolean afterReadmore = false;

            String[] wordsIneachLine = input.split("\n");

            for(int i=0; i<wordsIneachLine.length; i++){

                String line = wordsIneachLine[i];
                if(!line.equals("---") && !line.equals("READMORE") && !afterReadmore){
                    String[] temp = line.split(":");
                    if(temp.length == 2){
                        if(temp[1].contains("\"")){
                            jsonString = jsonString + "\"" + temp[0] + "\" : " +temp[1] + ",";  //"title": "What Are Annotations?",
                        } else {
                            jsonString = jsonString + "\"" + temp[0] + "\" : \"" + temp[1] + "\",";  //"title": "What Are Annotations?",
                        }

                    } else {
                        jsonString = jsonString + "\" short-content\" : \"" + line + "\",";
                    }
                } else if(line.equals("READMORE")){
                    afterReadmore = true;
                    jsonString = jsonString + "\" content\" : \",";
                } else if(afterReadmore){
                    jsonString = jsonString +  line ;
                }
            }


            jsonString += "\"}";
            JSONObject jsonObject = new JSONObject(jsonString);
            System.out.println(jsonObject);


        } catch(Exception e){
            System.out.println(e);
        }

    }
}
