package org.example.service;


import org.apache.jena.graph.Graph;
import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.example.database.DatabaseClass;
import org.example.model.Message;
import org.example.model.RDFEdge;
import org.example.model.RDFNode;

import java.io.InputStream;
import java.util.*;

//
//

public class MessageService {

    private static Map<Long, Message> messages = DatabaseClass.getMessages();
    private static ArrayList<RDFNode> nodes = new ArrayList<RDFNode>();
    private static ArrayList<RDFEdge> edges = new ArrayList<RDFEdge>();
    private static Set<RDFNode> nodesnew = new HashSet<RDFNode>();

    public MessageService() {

        messages.put(1l, new Message(1, "Hello", "Mohamad"));
        messages.put(2l, new Message(1, "Hello world!", "Mohamad rIhany"));
    }

    public Set<RDFNode> getNodesEdges(){

        String inputFileName1 = "/home/mohamad/Desktop/SafeCare/RDF-to-JSON/onto-individuals.owl";
        Model model = ModelFactory.createDefaultModel();
        InputStream inputStream = FileManager.get().open(inputFileName1);
        if (inputStream == null) {
            throw new IllegalArgumentException("File" + inputFileName1 + "not found");
        }

        //To wirte all the triples in JSON format
        //FileManager.get().addLocatorClassLoader(Main.class.getClassLoader());
        //Model model = FileManager.get().loadModel(inputFileName1);
        //model.write(System.out,"RDF/JSON");

        model.read(inputStream, "");
        Graph graph = model.getGraph();




        StmtIterator x = model.listStatements();
        if (!x.hasNext()) {
            System.out.println("...");
        }
        while (x.hasNext()) {
            Statement sol = x.next();
            Property property = sol.getPredicate();
            if(property.getLocalName().toString().equals("type") || property.getLocalName().toString().equals("safecareonto") || property.getLocalName().toString().equals("versionInfo") || property.getLocalName().toString().equals("imports")){
                continue;
            } else {
                String nodeSurce = sol.getSubject().asResource().getLocalName().toString();
                String nodeTarget = sol.getObject().asResource().getLocalName().toString();
                String edge = sol.getPredicate().getLocalName().toString();

                if(!nodes.isEmpty()){
                    //for(RDFNode node: nodes){
                        if(!nodes.contains(nodeSurce)) {
                            RDFNode n = new RDFNode(nodeSurce, nodeSurce);
                            nodes.add(n);
                            nodesnew.add(n);
                        }
                        if(!nodes.contains(nodeTarget)) {
                            RDFNode n = new RDFNode(nodeTarget, nodeTarget);
                            nodes.add(n);
                            nodesnew.add(n);
                        }
                        //if(!node.getId().equals(nodeSurce)) {
                            //RDFNode n = new RDFNode(nodeSurce, nodeSurce);
                            //nodes.add(n);
                        //}
                    }
                        //if(!node.getId().equals(nodeTarget)) {
                        //    RDFNode n = new RDFNode(nodeTarget, nodeTarget);
                        //    nodes.add(n);
                        //}
                    //}
                else{
                    RDFNode nSource = new RDFNode(nodeSurce, nodeSurce);
                    nodes.add(nSource);
                    nodesnew.add(nSource);
                    RDFNode nTarget = new RDFNode(nodeTarget, nodeTarget);
                    nodes.add(nTarget);
                    nodesnew.add(nTarget);

                }
                RDFEdge newEdge = new RDFEdge(edges.size()+1,nodeSurce,nodeTarget,edge);
                edges.add(newEdge);
            }
            System.out.println("..." + sol.toString());
        }
        return nodesnew;
    }

    public ArrayList<RDFEdge> getEdges(){
        return edges;
    }

    public static List<Message> getAllMessages(){
        return new ArrayList<Message>(messages.values());
    }

    public static Message getMessage(Long id){
        return messages.get(id);
    }

    public Message addMessage(Message message){
        message.setId(messages.size()+1);
        messages.put(message.getId(),message);
        return message;
    }

    public Message UpdateMessage(Message message){
        if(message.getId()<=0){
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public Message removeMessage(Long id){
        return messages.remove(id);
    }

    public static List<RDFNode> getNodes(){
        return nodes;
    }









    /*public static List<Message> getAllMessages(){
        Message m1 = new Message(1,"Hello", "Mohamad");
        Message m2 = new Message(2,"Hello World", "Mohamad");
        Message m3 = new Message(3,"Hello Mohamad!", "Mohamad");
        List<Message> listMessages = new ArrayList<Message>();
        listMessages.add(m1);
        listMessages.add(m2);
        listMessages.add(m3);

        return listMessages;


    }*/
}
