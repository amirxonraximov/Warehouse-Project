package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.AttachmentContent;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.AttachmentContentRepository;
import com.example.appwarehouse.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

@Service
public class AttachmentService {

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    public Result uploadFile(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        MultipartFile file = request.getFile(fileNames.next());
        Attachment attachment = new Attachment();
        assert file != null;
        attachment.setName(file.getOriginalFilename());
        attachment.setSize(file.getSize());
        attachment.setContentType(file.getContentType());
        Attachment savedAttachment = attachmentRepository.save(attachment);


        AttachmentContent attachmentContent = new AttachmentContent();
        attachmentContent.setBytes(file.getBytes());
        attachmentContent.setAttachment(savedAttachment);
        attachmentContentRepository.save(attachmentContent);
        return new Result("File saved", true, savedAttachment.getId());
    }

    public Optional<AttachmentContent> getAttachment(@PathVariable Integer id) throws IOException {
        Optional<AttachmentContent> optionalAttachment = attachmentContentRepository.findById(id);
        return optionalAttachment;
    }


}
