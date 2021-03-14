package daggertutorial;

import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;
import daggertutorial.Database.Account;

@Subcomponent(modules = UserCommandsModule.class)
interface UserCommandsRouter {
    CommandRouter router();

    @Subcomponent.Factory
    interface Factory {
        UserCommandsRouter create(@BindsInstance Account account);
    }

    @Module(subcomponents = UserCommandsRouter.class)
    interface InstallationModule {}
}
