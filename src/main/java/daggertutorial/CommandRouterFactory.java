package daggertutorial;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {UserCommandsModule.class, HelloWorldModule.class, LoginCommandModule.class, SystemOutModule.class})
public interface CommandRouterFactory {
    CommandRouter router();
}
