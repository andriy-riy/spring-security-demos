package com.rio.entity;

import com.rio.service.OrganizationEvent;
import java.util.List;
import lombok.Data;

@Data
public class Organization {

  private final String id;
  private final String name;
  private final List<OrganizationEvent> events;
  private final List<String> userIds;
}
