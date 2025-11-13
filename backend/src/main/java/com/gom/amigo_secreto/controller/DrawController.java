package com.gom.amigo_secreto.controller;

import com.gom.amigo_secreto.dto.draw.DrawResponseDTO;
import com.gom.amigo_secreto.service.DrawService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/draws")
public class DrawController {

    private final DrawService drawService;

    public DrawController(DrawService drawService) {
        this.drawService = drawService;
    }

    @GetMapping
    public ResponseEntity<Page<DrawResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        return ResponseEntity.ok(drawService.getAll(page, size));
    }

    @PostMapping("/{groupId}")
    public ResponseEntity<List<DrawResponseDTO>> createDraw(@PathVariable Long groupId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(drawService.createDraw(groupId));
    }
}
