package daggertutorial;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UserCommandsModule.class, HelloWorldModule.class, LoginCommandModule.class, SystemOutModule.class})
interface CommandProcessorFactory {
    CommandProcessor processor();
}
