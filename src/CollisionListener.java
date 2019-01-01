
public interface CollisionListener {

    //Collision for other things
    public <T extends VectorSprite> boolean collide(T object1, GraphicsObject object2);
    
    public <T extends VectorSprite> void collideAction(T object);
    
    
}
