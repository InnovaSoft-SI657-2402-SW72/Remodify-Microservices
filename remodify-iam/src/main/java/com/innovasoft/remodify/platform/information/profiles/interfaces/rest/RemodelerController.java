package com.innovasoft.remodify.platform.information.profiles.interfaces.rest;

import com.innovasoft.remodify.platform.information.profiles.domain.model.queries.GetAllRemodelerQuery;
import com.innovasoft.remodify.platform.information.profiles.domain.model.queries.GetRemodelerByIdQuery;
import com.innovasoft.remodify.platform.information.profiles.domain.model.queries.GetRemodelerByUserIdQuery;
import com.innovasoft.remodify.platform.information.profiles.domain.services.RemodelerCommandService;
import com.innovasoft.remodify.platform.information.profiles.domain.services.RemodelerQueryService;
import com.innovasoft.remodify.platform.information.profiles.interfaces.rest.resources.CreateRemodelerResource;
import com.innovasoft.remodify.platform.information.profiles.interfaces.rest.resources.RemodelerResource;
import com.innovasoft.remodify.platform.information.profiles.interfaces.rest.transform.CreateRemodelerCommandFromResourceAssembler;
import com.innovasoft.remodify.platform.information.profiles.interfaces.rest.transform.RemodelerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api/v1/remodelers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Remodelers", description = "Remodeler Management Endpoints")
public class RemodelerController {

    private final RemodelerQueryService remodelerQueryService;
    private final RemodelerCommandService remodelerCommandService;



    public RemodelerController(RemodelerQueryService remodelerQueryService, RemodelerCommandService remodelerCommandService) {
        this.remodelerQueryService = remodelerQueryService;
        this.remodelerCommandService = remodelerCommandService;
    }

    @PostMapping
    public ResponseEntity<RemodelerResource>createRemodeler(@RequestBody CreateRemodelerResource resource) {

        var createRemodelerCommand = CreateRemodelerCommandFromResourceAssembler.toCommandFromResource(resource);
        var remodelerId = remodelerCommandService.handle(createRemodelerCommand);

        if (remodelerId == 0L) {
            return ResponseEntity.badRequest().build();
        }

        var getRemodelerByIdQuery = new GetRemodelerByIdQuery(remodelerId);
        var remodeler = remodelerQueryService.handle(getRemodelerByIdQuery);

        if (remodeler.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        var remodelerResource = RemodelerResourceFromEntityAssembler.toResourceFromEntity(remodeler.get());
        return new ResponseEntity<>(remodelerResource, HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<RemodelerResource> getRemodelerByUserId(@PathVariable Long userId) {
        var getRemodelerByUserIdQuery = new GetRemodelerByUserIdQuery(userId);
        var remodeler = remodelerQueryService.handle(getRemodelerByUserIdQuery);
        if (remodeler.isEmpty()) return ResponseEntity.badRequest().build();
        var remodelerResource = RemodelerResourceFromEntityAssembler.toResourceFromEntity(remodeler.get());
        return new ResponseEntity<>(remodelerResource, HttpStatus.OK);
    }


    @GetMapping("/{remodelerId}")
    public ResponseEntity<RemodelerResource> getRemodelerById(@PathVariable Long remodelerId) {
        var getRemodelerByIdQuery = new GetRemodelerByIdQuery(remodelerId);
        var remodeler = remodelerQueryService.handle(getRemodelerByIdQuery);
        if (remodeler.isEmpty()) return ResponseEntity.badRequest().build();
        var remodelerResource = RemodelerResourceFromEntityAssembler.toResourceFromEntity(remodeler.get());
        return new ResponseEntity<>(remodelerResource, HttpStatus.OK);
    }
    


}

