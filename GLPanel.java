package com.ezardlabs.dethsquare.util;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLJPanel;

public class GLPanel extends GLJPanel {

	GLPanel(final BaseGame baseGame) {
		addGLEventListener(new GLEventListener() {
			@Override
			public void init(GLAutoDrawable glAutoDrawable) {
//				GL2 gl2 = glAutoDrawable.getGL().getGL2();
//				Utils.setGL2(gl2);
//
//				gl2.glClearColor(0.0f, 0.0f, 0.0f, 1);
//
//				gl2.glEnable(GL2.GL_BLEND);
//				gl2.glBlendFunc(GL2.GL_ONE, GL2.GL_ONE_MINUS_SRC_ALPHA);
//
//				int vertexShader = Utils.loadShader(GL2.GL_VERTEX_SHADER, ShaderTools.vsImage);
//				int fragmentShader = Utils.loadShader(GL2.GL_FRAGMENT_SHADER, ShaderTools.fsImage);
//
//				ShaderTools.spImage = gl2.glCreateProgram();
//				gl2.glAttachShader(ShaderTools.spImage, vertexShader);
//				gl2.glAttachShader(ShaderTools.spImage, fragmentShader);
//				gl2.glLinkProgram(ShaderTools.spImage);
//				gl2.glValidateProgram(ShaderTools.spImage);
//
//				gl2.glUseProgram(ShaderTools.spImage);
//
//				baseGame.create();
//
//				GameObject.startAll();
//				Renderer.init();
//				Collider.init();
			}

			@Override
			public void dispose(GLAutoDrawable glAutoDrawable) {
			}

			@Override
			public void display(GLAutoDrawable glAutoDrawable) {
				Utils.setGL2(glAutoDrawable.getGL().getGL2());
				GL2 gl2 = glAutoDrawable.getGL().getGL2();

//				baseGame.update();
//
//				Utils.setCameraPosition(Camera.main);
//
//				baseGame.render();
				gl2.glBegin (GL2.GL_LINES);//static field
				gl2.glVertex3f(0.50f,-0.50f,0);
				gl2.glVertex3f(-0.50f,0.50f,0);
				gl2.glEnd();

				GLPanel.this.repaint();
			}

			@Override
			public void reshape(GLAutoDrawable glAutoDrawable, int x, int y, int width, int
					height) {
//				baseGame.onResize(width, height);
//				Utils.onScreenSizeChanged(width, height);
			}
		});
	}
}
