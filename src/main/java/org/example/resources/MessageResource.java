package org.example.resources;

import org.example.model.Message;
import org.example.model.RDFEdge;
import org.example.model.RDFNode;
import org.example.service.MessageService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Set;

@Path("/message")
public class MessageResource {

    MessageService messageService = new MessageService();

    @GET
    @Produces(MediaType.APPLICATION_XML)
    public List<Message> getMassages(){
        return MessageService.getAllMessages();
    }

    @GET
    @Path("/{messageId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Message getMessage(@PathParam("messageId") Long messageId){
        return MessageService.getMessage(messageId);
    }

    @GET
    @Path(("/RDFnodes"))
    @Produces(MediaType.APPLICATION_JSON)
    public Set<RDFNode> getNodes(){
        return messageService.getNodesEdges();
                //MessageService.getNodes();
    }

    @GET
    @Path(("/RDFedges"))
    @Produces(MediaType.APPLICATION_JSON)
    public List<RDFEdge> getEdges(){
        return messageService.getEdges();
        //MessageService.getNodes();
    }
}
