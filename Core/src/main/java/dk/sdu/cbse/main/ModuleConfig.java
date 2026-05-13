package dk.sdu.cbse.main;

import dk.sdu.cbse.common.interfaces.IEntityProcessingService;
import dk.sdu.cbse.common.interfaces.IGamePluginService;
import dk.sdu.cbse.common.interfaces.IPostEntityProcessingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.ServiceLoader;
import static java.util.stream.Collectors.toList;

@Configuration
public class ModuleConfig {

    @Bean
    public Game game() {
        return new Game(gamePluginServices(), entityProcessingServices(), postEntityProcessingServices());
    }

    @Bean
    public List<IGamePluginService> gamePluginServices() {
        return ServiceLoader.load(IGamePluginService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }

    @Bean
    public List<IEntityProcessingService> entityProcessingServices() {
        return ServiceLoader.load(IEntityProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }

    @Bean
    public List<IPostEntityProcessingService> postEntityProcessingServices() {
        return ServiceLoader.load(IPostEntityProcessingService.class)
                .stream()
                .map(ServiceLoader.Provider::get)
                .collect(toList());
    }
}