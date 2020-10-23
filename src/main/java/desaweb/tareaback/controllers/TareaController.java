package desaweb.tareaback.controllers;

import desaweb.tareaback.dtos.TareaDTO;
import desaweb.tareaback.mappers.TareaMapper;
import desaweb.tareaback.services.TareaService;
import desaweb.tareaback.utils.WrapperResponse;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping(value = "/api")
public class TareaController {
    
    private TareaService tareaService;
    private TareaMapper tareaMapper;
    
    @Autowired
    public TareaController(TareaService tareaService, TareaMapper tareaMapper){
        this.tareaService = tareaService;
        this.tareaMapper = tareaMapper;
    }
    
    @GetMapping(value="/all")
    public ResponseEntity<List<TareaDTO>> findAll(@RequestParam(value = "pageNumber", required = false, defaultValue = "0") int pageNumber,
                                                  @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize){
        Pageable page = PageRequest.of(pageNumber,pageSize);
        List<TareaDTO> tareaDTOS = tareaMapper.toTareaDtos(tareaService.findAll(page));

        return new WrapperResponse(true,"success", tareaDTOS)
                .createResponse(HttpStatus.OK);
    }

    @GetMapping(value="/{id}")
    public ResponseEntity<TareaDTO> find(@PathVariable(value = "id") Long id){
        TareaDTO tareaDTO = tareaMapper.toTareaDto(tareaService.findbyId(id));
        return new WrapperResponse(true,"success", tareaDTO)
                .createResponse(HttpStatus.OK);
    }

    @PostMapping(value="")
    public ResponseEntity<TareaDTO> crear(@Valid @RequestBody TareaDTO tareaDTO, BindingResult result){
        if (result.hasErrors()){
            return new WrapperResponse(false,"", result.getAllErrors().stream().map(r->r.getDefaultMessage()).collect(Collectors.toList()))
                    .createResponse(HttpStatus.BAD_REQUEST);
        }
        TareaDTO newDto = tareaMapper.toTareaDto(tareaService.save(tareaMapper.toTarea(tareaDTO)));

        return new WrapperResponse(true,"success", newDto)
                .createResponse(HttpStatus.CREATED);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<TareaDTO> update(@Valid @RequestBody TareaDTO tareaDTO, BindingResult result
    ,@Valid @NotNull(message = "No puede venir vacio")
     @PathVariable(value = "id") Long id, BindingResult resultId
                                           ){
        if (result.hasErrors()){
            return new WrapperResponse(false,"", result.getAllErrors().stream().map(r->r.getDefaultMessage()).collect(Collectors.toList()))
                    .createResponse(HttpStatus.BAD_REQUEST);
        }
        if (resultId.hasErrors()){
            return new WrapperResponse(false,"", result.getAllErrors().stream().map(r->r.getDefaultMessage()).collect(Collectors.toList()))
                    .createResponse(HttpStatus.BAD_REQUEST);
        }
        TareaDTO newDto = tareaMapper.toTareaDto(tareaService.update(tareaMapper.toTarea(tareaDTO),id));

        return new WrapperResponse(true,"success", newDto)
                .createResponse(HttpStatus.OK);
    }


    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id")
                                             @NotNull(message = "No puede estar vacio")
                                                     Long id){
        tareaService.delete(id);
        return new WrapperResponse(true,"success",null).createResponse(HttpStatus.OK);
    }





}
