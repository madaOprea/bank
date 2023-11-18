package com.bankingsystem.bank.model.mapper;

import com.bankingsystem.bank.model.Document;
import com.bankingsystem.bank.model.dto.DocumentDto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    DocumentMapper DOCUMENT_MAPPER = Mappers.getMapper(DocumentMapper.class);

    DocumentDto documentToDocumentDto(Document document);
    Document documentDtoToDocument(DocumentDto documentDto);
}
