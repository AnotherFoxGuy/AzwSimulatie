package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.controllers.DocumentStoreHolder;
import com.nhlstenden.amazonsimulatie.models.generated.Robot;
import net.ravendb.client.documents.session.IDocumentSession;

public class RobotParkStrategy implements RobotTaskStrategy {
  private Node destination;

  public RobotParkStrategy(Node destination) {
    this.destination = destination;
  }

  public Node getDestination() {
    return destination;
  }

  @Override
  public void execute(RobotImp robotImp) {
    try (IDocumentSession session = DocumentStoreHolder.getStore().openSession()) {
      Robot robotP = session.load(Robot.class, robotImp.getId());

      robotP.setStatus(Robot.Status.IDLE);

      session.saveChanges();
      robotImp.taskDone(this);
    }
  }
}