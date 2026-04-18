package com.autobots.automanager.controles;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GersonControle {
	private static final String GERSINHO = String.join("\n",
	"""           
                                             .:;+++++++xxxxx+;.                                     
                                           .;;;::::::::::::;;;++.                                   
                                          :+:.................:;+;                                  
                                         :;:...................::;;                                 
                                        +:......................:::+:                               
                                      .+:.......................::::+:                              
                                      ;;:.......................::::++.                             
                                     .+;:::;;;++;;::.....:;+++++;:::+x:                             
                                     .+;:::::..:::::....::::::::;+;;;x:                             
                                     .+;:::::++;;;:::..:::;;;;++;;;;;x:                             
                                     :+;:::;;+++;;:::.::::;:+++++;:;;x;                             
                                     :+:..:::::::::::..:::::::::::::;x;                             
                                    .;+:......:..::....::::......:::;x;.                            
                                    .;;:........:::;:::;;:::.....:::;x+.                            
                                    .;;::.....::::;::;;;;;::::..::::;++.                            
                                    .::::::::::::::.....::::::::::;;;;;                    
                                    ..::::::::::;;;;::;;;;;;:::::;;;;;.. 
                                     .::::::::;++;:.. ....::++::::;:;;;. 
                                     ..::::..::;:;++:;:;;++;;:..:::;;:.
                                       .:::::::::::::::::;;:::.:::;;..         
                                        :;:::::::::.::::::::::::;;;;..
                                        .;;;::::::::::::::::::;;;+;:..
                                        :;;;::::::::.::::::;;++++;:. 
                                        .:;;;;;;::::::::::;;++++++;:. 
                                    ..:+;:;;;;;;;;;;;++++++++++++;;;+;.           
                                 .;+++: ;;::;;;++++++++++++++++;;;;;;xx+++;          
                         .  ..:++x++x:  ::::::;;;;+++++++++++;;;;;;:.+xxxxxx+;:          
                       ...:+++++xxxxx+. .::::::;;;;:::;;;;;;;;:::;: .xxxxxxxx+++:          
                   ..:;+++++++xxxxxxx+. .:::::::;;:::;;;::::::::. .xxxxxxxxxxx+++;         
                 :++++++++xxxxxxxxxxxx;  .:::::::::::::::::::...+xxxxxxxxxxxxxxx++++;        
                .;+++++++xxxxxxxxxxxxxxxxx;....::::::::::::....:+xxxxxxxxxxxxxxxxxxxx+++;:..        
               :+++xxxxxxxxxxxxxxxxxxxxxxxxxx++;:::........:;+xxxxxxxxxxxxxxxxxxxxxxxxxx+++;:       
              ++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+;.     
              ++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+.    
             ++xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+:.  
            ;+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx+.  
           :+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx;..
           ;+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx:.
          :+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxxxxx+:
          :+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxXxxxxxxxx:
          ;xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxxXXXxXXXXxx+
         :+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXxxx
         ;xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXxXXXXXx
        :+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXXXXXXXXxxx
        +xxxxxxxxxXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXxXXXXXXXXXXXXXxxxxxX
       :xxxxxxxxxxXXX$$XXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXX$$XXXXXXxxXXXX
       +xxxxxxxxxxXXX$XxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXX$$XXXXXXXXXxxx
      +xxxxxxxxxxXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXx;+$$$XXXXXXXxxx
     +xxxxxxxxxXXXXXXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXx;;;;+XXXXXXXXXXX
    ;xxxxxxxxXXXXXX$$XxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXx;;;;;;;xXXXXXXXXX
   :+xxxxxxxXXXXXX$$X;+xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXX+;;;;;;;;;XXXXXXXX
  :xxxxxxxxxXXXXX$$$;:;xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXx;;;;;;;;;;+XXXXXXX
  +xxxxxxXXXXXXXXXXX:.;xXxxxxXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXX;;;;;;;;;;;;;xxXXXX
 :xxxxxxxxXXXXXXXXX+..:+XXXxxXXXxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxXXXXXXXXX+;;;;;;;;;;;;;;;;+xX""");

	@GetMapping(value = "/gerson", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> mostrarGerson() {
		return ResponseEntity.ok(GERSINHO);
	}
}
