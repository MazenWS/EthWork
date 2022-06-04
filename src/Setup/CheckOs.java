package Setup;




public class CheckOs {
    private static OS os = null;
    public static OS CheckOS() throws Exception {

        if (os == null) {
            String operSys = System.getProperty("os.name").toLowerCase();
            if (operSys.contains("win")) {
                os = OS.WINDOWS;
            } else if (operSys.contains("nix") || operSys.contains("nux")
                    || operSys.contains("aix")) {
                os = OS.LINUX;
            } else if (operSys.contains("mac")) {
                os = OS.MAC;
            }
            else {
              throw  new Exception(" This operating system is not supported by the framework");
            }
        }
        return os;


    }
    public static void main (String[]args){

        try {
            System.out.println( CheckOS());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }}
