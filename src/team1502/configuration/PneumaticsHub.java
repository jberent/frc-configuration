package team1502.configuration;

public class PneumaticsHub {
    public PneumaticsHub Compressor(String name) {
        return this;
    }
    public PneumaticsHub Solenoid(int module, int channel, String name) {
        return this;
    }
    public PneumaticsHub DoubleSolenoid(int forwardChannel, int reverseChannel, String name) {
        return this;
    }

}
