package com.mycompany.filesmanipulator.controller;

import com.mycompany.filesmanipulator.entity.TextFile;
import com.mycompany.filesmanipulator.service.TextFilesService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author andrew
 */
@Path("textFilesController")
public class TextFilesController {
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllFiles() {
        TextFilesService textFilesService = new TextFilesService();
        List<TextFile> textFiles = textFilesService.getAllFilesFromFolder();
        GenericEntity<List<TextFile>> jsonObjests = new GenericEntity<List<TextFile>>(textFiles) {};
        return Response.ok(jsonObjests).build();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void saveFile(String text) {
        TextFilesService textFilesService = new TextFilesService();
        textFilesService.saveFile(text);
    }
}
