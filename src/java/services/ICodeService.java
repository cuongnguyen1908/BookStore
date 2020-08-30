package services;

import dtos.CodeDTO;

import java.util.List;

public interface ICodeService {
    Long save(CodeDTO dto, String date);
    List<CodeDTO> findAll();
    boolean existCodeByName(String name);
    CodeDTO findCodeByName(String code);
}
