package daggertutorial;

import dagger.Module;
import dagger.Provides;

@Module
abstract class SystemOutModule {

    @Provides
    static Outputter textOutputter() {
        return new Outputter(){

            @Override
            public void output(String output) {
                System.out.println(output);
				
            }
            
        };
    }
}
