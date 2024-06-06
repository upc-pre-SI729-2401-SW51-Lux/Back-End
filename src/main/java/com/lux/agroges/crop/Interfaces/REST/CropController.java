package com.lux.agroges.crop.Interfaces.REST;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="api/v1/crops",produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Crops",description = "Crop endpoints")
public class CropController {
}
