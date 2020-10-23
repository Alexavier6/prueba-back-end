package desaweb.tareaback.mappers;

import desaweb.tareaback.dtos.TareaDTO;
import desaweb.tareaback.entitys.Tarea;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TareaMapper {
    @Mappings({
            @Mapping(source = "descripcion", target = "descripcion"),
            @Mapping(source = "fechaCreacion", target = "fechaCreacion" ,dateFormat = "dd-MM-yyyy"),
            @Mapping(source = "vigente.descripcion", target = "vigente")
    })
    TareaDTO toTareaDto(Tarea tarea);
    List<TareaDTO> toTareaDtos(List<Tarea> tareas);

    @InheritInverseConfiguration
    @Mapping(target = "idTarea", ignore = true)
    Tarea toTarea(TareaDTO tareaDTO);
    List<Tarea> toTareas(List<TareaDTO> tareaDTOS);
}
