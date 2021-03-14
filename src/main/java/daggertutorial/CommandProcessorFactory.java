package daggertutorial;

import javax.inject.Singleton;
import dagger.Component;


@Singleton
@Component(modules = { HelloWorldModule.class, LoginCommandModule.class,
        SystemOutModule.class, UserCommandsRouter.InstallationModule.class })
interface CommandProcessorFactory {
    CommandProcessor processor();
}
