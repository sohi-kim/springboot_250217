package org.iclass.board.repository;

// Dto 대신, 사용하는 인터페이스 -> Map 역할
public interface UserCountProjection {
  String getUsername();
  Long getCount();
}
