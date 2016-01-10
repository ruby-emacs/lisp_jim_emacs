(defconst pi 3 "Pi to one place.")
pi ;=> 3
(setq pia 4)
pia ;=> 4
(defconst neo-buffer-name " *NeoTree*"
  "Name of the buffer where neotree shows directory contents.")
neo-buffer-name ;=> " *NeoTree*"

(expand-file-name "foo") ;=> "/Users/jim/JIM_EMACS/neotree/foo"

load-file-name ;=> nil

(file-name-directory "README.md") ;=> nil , ??

load-file-name ; => nil
default-directory ;=> "~/JIM_EMACS/neotree/"

(defconst neo-header-height 5) 
