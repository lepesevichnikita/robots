package org.klaster.service;

/*
 * practice
 *
 * 12.02.2020
 *
 */

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.klaster.model.entity.Skill;

/**
 * SkillsCache
 *
 * @author Nikita Lepesevich
 */

public class SkillsCache {

  private static SkillsCache instance;
  private Map<String, Skill> skills;

  private SkillsCache() {
    skills = Collections.synchronizedMap(new LinkedHashMap<>());
  }

  public static SkillsCache getInstance() {
    if (instance == null) {
      synchronized (SkillsCache.class) {
        instance = new SkillsCache();
      }
    }
    return instance;
  }

  public Skill getByName(String skillName) {
    return skills.get(skillName);
  }

  public Set<Skill> getByNames(List<String> skillNames) {
    return skillNames.stream()
                     .map(this::getByName)
                     .collect(Collectors.toSet());
  }

  public Skill getOrCreateByName(String skillName) {
    return skills.computeIfAbsent(skillName, Skill::new);
  }


  public Set<Skill> getOrCreateByNames(List<String> skillNames) {
    return skillNames.stream()
                     .map(this::getOrCreateByName)
                     .collect(Collectors.toSet());
  }
}
