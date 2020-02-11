package org.klaster.builder;/*
 * practice
 *
 * 11.02.2020
 *
 */

import java.util.Set;
import org.klaster.model.context.Job;
import org.klaster.model.entity.EmployerProfile;
import org.klaster.model.entity.JobSkill;

/**
 * JobBuilder
 *
 * @author Nikita Lepesevich
 */

public interface JobBuilder extends Builder<Job> {

  JobBuilder setEmployerProfile(EmployerProfile employerProfile);

  JobBuilder setDescription(String description);

  JobBuilder setSkills(Set<JobSkill> skills);

}
