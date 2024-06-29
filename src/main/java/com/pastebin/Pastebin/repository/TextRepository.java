package com.pastebin.Pastebin.repository;

import com.pastebin.Pastebin.models.TextContent;
import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<TextContent, Long> {

}
