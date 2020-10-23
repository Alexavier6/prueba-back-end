package desaweb.tareaback.services;

import desaweb.tareaback.entitys.Tarea;
import desaweb.tareaback.exceptions.GeneralServiceException;
import desaweb.tareaback.exceptions.NoDataFoundException;
import desaweb.tareaback.exceptions.ValidateServiceException;
import desaweb.tareaback.repositorys.TareaRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.annotation.RuntimeAnnos;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Slf4j
@Service
public class TareaService {

    private TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository){
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> findAll(Pageable page){
        return tareaRepository.findAll(page).toList();
    }

    public Tarea findbyId(Long id){
        try {
            return tareaRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("No existe la tarea"));
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }


    @Transactional
    public Tarea save(Tarea newTarea){
        try {
            return tareaRepository.save(newTarea);
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public Tarea update(Tarea newTarea, Long id){
        try {
            Tarea tarea = findbyId(id);
            tarea.setDescripcion(newTarea.getDescripcion());
            tarea.setVigente(newTarea.getVigente());
            return tareaRepository.save(tarea);
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }

    @Transactional
    public void delete(Long id){
        try {
            Tarea tarea = findbyId(id);
            tareaRepository.delete(tarea);
        }catch (ValidateServiceException | NoDataFoundException e){
            log.info(e.getMessage(), e);
            throw e;
        }catch (Exception e){
            log.error(e.getMessage(),e);
            throw new GeneralServiceException(e.getMessage(),e);
        }
    }



}
