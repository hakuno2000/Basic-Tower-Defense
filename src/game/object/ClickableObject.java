package game.object;

public interface ClickableObject {
    void onHover(int mouseX, int mouseY, Object caller);
    void onClick(int mouseX, int mouseY, Object caller);
}
