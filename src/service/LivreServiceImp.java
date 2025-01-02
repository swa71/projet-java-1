package service;

import entity.Livre;

import java.util.List;

public class LivreServiceImp implements LivreService {
  @Override
  public List<Livre> getAllLivre() {
    return List.of();
  }

  @Override
  public Livre getLivreById(int id) {
    return null;
  }

  @Override
  public boolean addLivre(Livre livre) {
    return false;
  }

  @Override
  public boolean updateLivre(Livre livre) {
    return false;
  }

  @Override
  public boolean deleteLivre(int id) {
    return false;
  }
}
