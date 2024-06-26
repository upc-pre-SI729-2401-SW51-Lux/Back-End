package com.lux.agroges.crop.Interfaces.REST;

import com.lux.agroges.crop.Domain.Model.commands.AddProductToCropCommand;
import com.lux.agroges.crop.Domain.Model.commands.DeleteCropCommand;
import com.lux.agroges.crop.Domain.Model.queries.GetAllCropsQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropByIdQuery;
import com.lux.agroges.crop.Domain.Model.queries.GetCropItemsByCropId;
import com.lux.agroges.crop.Domain.services.*;
import com.lux.agroges.crop.Interfaces.REST.Resources.*;
import com.lux.agroges.crop.Interfaces.REST.Transform.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="api/v1/crop",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Crop",description = "Crop endpoints")
public class CropController {
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }
    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource){
        if(resource.Id()==null){
            return ResponseEntity.badRequest().build();
        }
        var createCropCommand= CreateCropCommandFromResourceAssembler.toCommandFromResource(resource);
        var crop=cropCommandService.handle(createCropCommand);
        if(crop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cropResource= CropFromEntityAssembler.toResourceFromEntity(crop.get());

        return new ResponseEntity<>(cropResource, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<CropResource>>getCrop(){
        var getAllCropQuery= new GetAllCropsQuery();
        var crops=cropQueryService.handle(getAllCropQuery);
        var cropResources= crops.stream().map(CropFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(cropResources);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long Id){
        var getCropQuery= new GetCropByIdQuery(Id);
        var crop=cropQueryService.handle(getCropQuery);
        if(crop.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var cropResource= CropFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
    @PutMapping("/{Id}")
    public ResponseEntity<CropResource> updateCrop(@PathVariable Long Id,@RequestBody UpdateCropResource resource){
        var UpdateCrop=UpdateCropCommandFromResourceAssembler.toCommandFromResource(Id,resource);
        var crop=cropCommandService.handle(UpdateCrop);
        if(crop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cropResource= CropFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrop(@PathVariable Long id) {
        var deleteCropCommand = new DeleteCropCommand(id);
        cropCommandService.handle(deleteCropCommand);
        return ResponseEntity.ok("Crop deleted");
    }

    @PutMapping("/{Id}/products/{productId}")
    public ResponseEntity<CropItemResource>addProductToCrop(@PathVariable Long productId,@PathVariable Long Id){
        cropCommandService.handle(new AddProductToCropCommand(Id,productId));
        var cropByIdQuery= new GetCropItemsByCropId(Id);
        var cropItem=cropQueryService.handle(cropByIdQuery);
        if(cropItem.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            var cropItemResource=CropItemFromResourceToEntityAssembler.toResourceFromEntity(cropItem.getFirst());
            return ResponseEntity.ok(cropItemResource);
        }
    }

}
