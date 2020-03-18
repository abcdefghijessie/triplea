package games.strategy.engine.lobby.client.ui.action;

import games.strategy.engine.lobby.connection.PlayerToLobbyConnection;
import javax.annotation.Nonnull;
import javax.swing.Action;
import javax.swing.JFrame;
import lombok.Builder;
import org.triplea.domain.data.PlayerChatId;
import org.triplea.http.client.lobby.moderator.BanPlayerRequest;
import org.triplea.swing.SwingAction;

@Builder
public class BanPlayerModeratorAction {
  @Nonnull private final PlayerToLobbyConnection playerToLobbyConnection;
  @Nonnull private final JFrame parent;
  @Nonnull private final PlayerChatId playerChatIdToBan;

  public Action toSwingAction() {
    return SwingAction.of(
        "Ban Player",
        e ->
            BanDurationDialog.prompt(
                parent,
                "Select Timespan",
                "Please consult other admins before banning longer than 1 day. \n"
                    + "And please remember to report this ban.",
                timespan ->
                    // do confirmation
                    playerToLobbyConnection.banPlayer(
                        BanPlayerRequest.builder()
                            .playerChatId(playerChatIdToBan.getValue())
                            .banMinutes(timespan.toMinutes())
                            .build())));
  }
}
