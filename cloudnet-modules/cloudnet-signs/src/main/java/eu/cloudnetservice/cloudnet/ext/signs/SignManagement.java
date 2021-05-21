package eu.cloudnetservice.cloudnet.ext.signs;

import de.dytanic.cloudnet.ext.bridge.WorldPosition;
import eu.cloudnetservice.cloudnet.ext.signs.configuration.SignsConfiguration;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * Represents a shared management for the sign system.
 */
public interface SignManagement {

    /**
     * Get a sign which is located at the specified location.
     *
     * @param position the position the sign is located at
     * @return the sign at the given location or null if there is no sign
     */
    @Nullable Sign getSignAt(@NotNull WorldPosition position);

    void createSign(@NotNull Sign sign);

    void deleteSign(@NotNull Sign sign);

    void deleteSign(@NotNull WorldPosition position);

    int deleteAllSigns(@NotNull String group);

    int deleteAllSigns(@NotNull String group, @Nullable String templatePath);

    int deleteAllSigns();

    @NotNull Collection<Sign> getSigns();

    @NotNull Collection<Sign> getSigns(@NotNull String[] group);

    @NotNull SignsConfiguration getSignsConfiguration();

    void setSignsConfiguration(@NotNull SignsConfiguration configuration);

    // Internal methods

    @ApiStatus.Internal
    void registerToServiceRegistry();

    @ApiStatus.Internal
    void unregisterFromServiceRegistry();

    @ApiStatus.Internal
    void handleInternalSignCreate(@NotNull Sign sign);

    @ApiStatus.Internal
    void handleInternalSignRemove(@NotNull WorldPosition position);

    @ApiStatus.Internal
    void handleInternalSignConfigUpdate(@NotNull SignsConfiguration configuration);
}
