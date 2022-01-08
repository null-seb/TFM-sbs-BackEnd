package es.upm.tfm_sbs.service.edu.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import es.upm.tfm_sbs.service.edu.entity.Subject;
import es.upm.tfm_sbs.service.edu.entity.query.SubjectQuery;
import es.upm.tfm_sbs.service.edu.listener.ExcelSubjectDataListener;
import es.upm.tfm_sbs.service.edu.mapper.SubjectMapper;
import es.upm.tfm_sbs.service.edu.service.SubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import es.upm.tfm_sbs.service.edu.util.ExcelSubjectData;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void batchImport(InputStream inputStream) {

        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(inputStream, ExcelSubjectData.class, new ExcelSubjectDataListener(baseMapper))
                .excelType(ExcelTypeEnum.XLSX).sheet().doRead();

    }

    @Override
    public List<SubjectQuery> nestedList() {
        return baseMapper.selectNestedListByParentId("0");
    }
}
