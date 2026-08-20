package org.wadajo.dto;

import org.wadajo.model.Obra;

import java.util.List;

public record ObraResponse(List<Obra> data) {}