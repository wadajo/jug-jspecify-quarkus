package org.wadajo.model;

import org.jspecify.annotations.Nullable;

import java.time.Year;

public record Obra(
    @Nullable String artist_title,
    String title,
    @Nullable Year date_end,
    @Nullable String description
) {}