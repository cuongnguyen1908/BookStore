package services.impl;

import daos.ICodeDAO;
import dtos.CodeDTO;
import services.ICodeService;

import javax.inject.Inject;
import java.util.List;

public class CodeService implements ICodeService {
    @Inject
    private ICodeDAO codeDAO;

    @Override
    public Long save(CodeDTO dto, String date) {
        return this.codeDAO.save(dto, date);
    }

    @Override
    public List<CodeDTO> findAll() {
        return this.codeDAO.findAll();
    }

    @Override
    public boolean existCodeByName(String name) {

        return this.codeDAO.existCodeByName(name);
    }

    @Override
    public CodeDTO findCodeByName(String code) {
        return this.codeDAO.findCodeByName(code);
    }
}
