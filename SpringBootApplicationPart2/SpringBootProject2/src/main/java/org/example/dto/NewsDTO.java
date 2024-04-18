package org.example.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.entites.Category;

import java.time.Instant;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

    private Long id;
    private String title;
    private String text;
    private LocalDateTime date = LocalDateTime.now();
    private String category;

    @Override
    public String toString() {
        return "NewsDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", date=" + date +
                ", category=" + category +
                '}';
    }
}
