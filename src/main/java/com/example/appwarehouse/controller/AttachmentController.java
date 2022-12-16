package com.example.appwarehouse.controller;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.AttachmentContent;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/attachment")
public class AttachmentController {

    @Autowired
    AttachmentService attachmentService;

    @PostMapping("/upload")
    public Result upload(MultipartHttpServletRequest request) throws IOException {

        Result result = attachmentService.uploadFile(request);
        return result;
    }

    @GetMapping("/{id}")
    public void getAttachment(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        Optional<AttachmentContent> contentOptional = attachmentService.getAttachment(id);
        if (contentOptional.isPresent()) {
            AttachmentContent attachmentContent = contentOptional.get();
            Attachment attachment = attachmentContent.getAttachment();
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + attachment.getName() + "\"");
            response.setContentType(attachment.getContentType());
            FileCopyUtils.copy(attachmentContent.getBytes(), response.getOutputStream());
        }
    }


}
