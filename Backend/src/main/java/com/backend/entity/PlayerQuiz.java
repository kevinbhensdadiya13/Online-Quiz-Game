package com.backend.entity;

import com.backend.enums.PlayerQuizStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerQuiz extends Audit {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  @ManyToOne private Quiz quiz;

  private Integer players = 0;

  @OneToMany(mappedBy = "playerQuiz")
  private List<PlayerQuizQuestion> questions;

  private long time;
  private int code;

  @Enumerated(EnumType.STRING)
  private PlayerQuizStatus status;

  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;

  @OneToOne private User user;
}
