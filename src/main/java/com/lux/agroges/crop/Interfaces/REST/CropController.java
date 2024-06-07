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
@RequestMapping(value="api/v1/crops",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Crops",description = "Crop endpoints")
public class CropController {
    private final CropCommandService cropCommandService;
    private final CropQueryService cropQueryService;

    public CropController(CropCommandService cropCommandService, CropQueryService cropQueryService) {
        this.cropCommandService = cropCommandService;
        this.cropQueryService = cropQueryService;
    }
    @PostMapping
    public ResponseEntity<CropResource> createCrop(@RequestBody CreateCropResource resource){
        if(resource.cropId()==null){
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

    @GetMapping("/{cropId}")
    public ResponseEntity<CropResource> getCropById(@PathVariable Long cropId){
        var getCropQuery= new GetCropByIdQuery(cropId);
        var crop=cropQueryService.handle(getCropQuery);
        if(crop.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        var cropResource= CropFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
    @PutMapping("/{cropId}")
    public ResponseEntity<CropResource> updateCrop(@PathVariable Long cropId,@RequestBody UpdateCropResource resource){
        var UpdateCrop=UpdateCropCommandFromResourceAssembler.toCommandFromResource(cropId,resource);
        var crop=cropCommandService.handle(UpdateCrop);
        if(crop.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        var cropResource= CropFromEntityAssembler.toResourceFromEntity(crop.get());
        return ResponseEntity.ok(cropResource);
    }
    @DeleteMapping ("/{cropId}")
    public ResponseEntity<?>deleteCrop(@PathVariable Long cropId){
        var deleteCropCommand= new DeleteCropCommand(cropId);
        cropCommandService.handle(deleteCropCommand);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{cropId}/products/{productId}")
    public ResponseEntity<CropItemResource>addProductToCrop(@PathVariable Long productId,@PathVariable Long cropId){
        cropCommandService.handle(new AddProductToCropCommand(cropId,productId));
        var cropByIdQuery= new GetCropItemsByCropId(cropId);
        var cropItem=cropQueryService.handle(cropByIdQuery);
        if(cropItem.isEmpty()){
            return ResponseEntity.notFound().build();
        }else{
            var cropItemResource=CropItemFromResourceToEntityAssembler.toResourceFromEntity(cropItem.getFirst());
            return ResponseEntity.ok(cropItemResource);
        }
    }

}
